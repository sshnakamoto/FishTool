package com.github.nakamotossh.fishtool.fragments;import android.content.Intent;import android.database.Cursor;import android.net.Uri;import android.os.Bundle;import android.support.annotation.NonNull;import android.support.annotation.Nullable;import android.support.design.widget.FloatingActionButton;import android.support.v4.app.Fragment;import android.support.v4.app.LoaderManager;import android.support.v4.content.CursorLoader;import android.support.v4.content.Loader;import android.util.Log;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.ImageView;import android.widget.TextView;import com.github.nakamotossh.fishtool.R;import com.github.nakamotossh.fishtool.activity.AquaNew;import com.github.nakamotossh.fishtool.database.AquaDbHelper;import java.util.Objects;import static com.github.nakamotossh.fishtool.database.AquaContract.AquaEntry.AQUA_CONTENT_URI;import static com.github.nakamotossh.fishtool.database.AquaContract.AquaEntry.CO2_COLUMN;import static com.github.nakamotossh.fishtool.database.AquaContract.AquaEntry.DOSAGE_COLUMN;import static com.github.nakamotossh.fishtool.database.AquaContract.AquaEntry.FILTER_COLUMN;import static com.github.nakamotossh.fishtool.database.AquaContract.AquaEntry.IMAGE_URI_COLUMN;import static com.github.nakamotossh.fishtool.database.AquaContract.AquaEntry.LIGHT_COLUMN;import static com.github.nakamotossh.fishtool.database.AquaContract.AquaEntry.LITERS_COLUMN;import static com.github.nakamotossh.fishtool.database.AquaContract.AquaEntry.NAME_COLUMN;import static com.github.nakamotossh.fishtool.database.AquaContract.AquaEntry.NOTES_COLUMN;import static com.github.nakamotossh.fishtool.database.AquaContract.AquaEntry.SIZE_COLUMN;import static com.github.nakamotossh.fishtool.database.AquaContract.AquaEntry.STATUS_COLUMN;import static com.github.nakamotossh.fishtool.database.AquaContract.AquaEntry.SUBSTRATE_COLUMN;import static com.github.nakamotossh.fishtool.database.AquaContract.AquaEntry.TYPE_COLUMN;/** * A simple {@link Fragment} subclass. */public class AquaFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {    private static final String TAG = "AquaFragment";    private final int AQUA_LOADER = 10;    private final String unknown = "Unknown";    private AquaDbHelper dbHelper;    private TextView aquaLiters;    private TextView aquaSize;    private TextView aquaLight;    private TextView aquaFilter;    private TextView aquaCo2;    private TextView aquaDose;    private TextView aquaSubstrate;    private TextView aquaType;    private TextView aquaStatus;    private TextView aquaDate;    private TextView aquaNote;    private ImageView aquaImage;    private boolean hasExtra = false;    private Uri aquaIdUri;    public AquaFragment() {        // Required empty public constructor    }    @Override    public void onCreate(@Nullable Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        dbHelper = new AquaDbHelper(getActivity());        /* Check if has arguments */        if (getArguments() == null)            throw new IllegalArgumentException(this.getClass().getSimpleName() + " " +                    "must always be called with arguments. Arguments could not be null.");        /* Get ID */        int intentAquaId = Objects.requireNonNull(Objects.requireNonNull(getActivity())                .getIntent()                .getExtras())                .getInt("aquaId");        /* Convert to URI */        aquaIdUri = Uri.withAppendedPath(AQUA_CONTENT_URI, String.valueOf(intentAquaId));    }    @Override    public View onCreateView(LayoutInflater inflater, ViewGroup container,                             Bundle savedInstanceState) {        View rootView = inflater.inflate(R.layout.fragment_aquainfo, container, false);        /* Find on layout */        aquaLiters = rootView.findViewById(R.id.aqua_info_liters);        aquaSize = rootView.findViewById(R.id.aqua_info_size);        aquaLight = rootView.findViewById(R.id.aqua_info_light);        aquaFilter = rootView.findViewById(R.id.aqua_info_filter);        aquaCo2 = rootView.findViewById(R.id.aqua_info_co2);        aquaDose = rootView.findViewById(R.id.aqua_info_dosage);        aquaSubstrate = rootView.findViewById(R.id.aqua_info_substrate);        aquaType = rootView.findViewById(R.id.aqua_info_type);        aquaStatus = rootView.findViewById(R.id.aqua_info_status);        aquaImage = rootView.findViewById(R.id.aqua_info_image);        aquaNote = rootView.findViewById(R.id.aqua_info_notes);        FloatingActionButton fab = rootView.findViewById(R.id.fab);        fab.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                startActivity(new Intent(getActivity(), AquaNew.class)                                .putExtra("aquaId", String.valueOf(aquaIdUri)));            }        });        // Inflate the layout for this fragment        return rootView;    }    @Override    public void onActivityCreated(@Nullable Bundle savedInstanceState) {        /* Starts Loader */        getLoaderManager().initLoader(AQUA_LOADER,null, this);        super.onActivityCreated(savedInstanceState);    }    @NonNull    @Override    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {        return new CursorLoader(getContext(),                aquaIdUri,                null,                null,                null,                null);    }    @Override    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor c) {        /* Get All Fields */        if (c.moveToNext()) {            String name = c.getString(c.getColumnIndexOrThrow(NAME_COLUMN));            String liters = c.getString(c.getColumnIndexOrThrow(LITERS_COLUMN));            //String date = c.getString(c.getColumnIndexOrThrow(DATE_AQUA_COLUMN));            int type = c.getInt(c.getColumnIndexOrThrow(TYPE_COLUMN));            int status = c.getInt(c.getColumnIndexOrThrow(STATUS_COLUMN));            String co2 = c.getString(c.getColumnIndexOrThrow(CO2_COLUMN));            String dosage = c.getString(c.getColumnIndexOrThrow(DOSAGE_COLUMN));            String substrate = c.getString(c.getColumnIndexOrThrow(SUBSTRATE_COLUMN));            String notes = c.getString(c.getColumnIndexOrThrow(NOTES_COLUMN));            String image = c.getString(c.getColumnIndexOrThrow(IMAGE_URI_COLUMN));            String size = c.getString(c.getColumnIndexOrThrow(SIZE_COLUMN));            String filter = c.getString(c.getColumnIndexOrThrow(FILTER_COLUMN));            String light = c.getString(c.getColumnIndexOrThrow(LIGHT_COLUMN));            /* Title */            getActivity().setTitle(name != null ? name : getActivity().getTitle());            /* Image */            if (image != null){                aquaImage.setImageURI(Uri.parse(image));            }            /* Liters */            aquaLiters.setText(liters != null && !liters.isEmpty() ? liters + " Liters" : unknown);            /* Co2 */            aquaCo2.setText(co2 != null && !co2.isEmpty() ? co2 : unknown);            /* Dosage */            aquaDose.setText(dosage != null && !dosage.isEmpty() ? dosage : unknown);            /* Substrate */            aquaSubstrate.setText(substrate != null && !substrate.isEmpty() ? substrate : unknown);            /* Notes */            aquaNote.setText(notes != null && !notes.isEmpty() ? notes : unknown);            /* Size */            aquaSize.setText(size != null && !size.isEmpty() ? size : unknown);            /* Filter */            aquaFilter.setText(filter != null && !filter.isEmpty() ? filter : unknown);            /* Light */            aquaLight.setText(light != null && !light.isEmpty() ? light : unknown);            /* Status */            final int STATUS_WORKING = 0;            final int STATUS_DISABLED = 1;            aquaStatus.setText(status == STATUS_WORKING ? "Working good" : "Disabled");            /* Type */            final int FRESHWATER = 0;            final int MARINE = 1;            final int BRACKISH = 2;            Log.d(TAG, "onLoadFinished: type: " + type);            switch (type) {                case FRESHWATER:                    aquaType.setText("Freshwater Aquarium");                    break;                case MARINE:                    aquaType.setText("Marine Aquarium");                    break;                case BRACKISH:                    aquaType.setText("Brackish Aquariums");                    break;                default:                    aquaType.setText(unknown);            }        }    }    @Override    public void onLoaderReset(@NonNull Loader<Cursor> loader) {    }}