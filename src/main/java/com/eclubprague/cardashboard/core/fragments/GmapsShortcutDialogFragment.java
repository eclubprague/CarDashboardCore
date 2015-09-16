package com.eclubprague.cardashboard.core.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.adapters.StringListAdapter;
import com.eclubprague.cardashboard.core.model.resources.IconResource;
import com.eclubprague.cardashboard.core.model.resources.StringResource;
import com.eclubprague.cardashboard.core.model.validation.IndependentTextValidator;
import com.eclubprague.cardashboard.core.model.validation.SimpleTextValidator;
import com.eclubprague.cardashboard.core.model.validation.TextValidationConfig;
import com.eclubprague.cardashboard.core.views.TextIconListItemView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michael on 09.09.2015.
 */
public class GmapsShortcutDialogFragment extends DialogFragment implements SimpleTextValidator.OnValidListener {

    private static final String TAG = GmapsShortcutDialogFragment.class.getSimpleName();

    private static final int TYPE_DISPLAY = 0;
    private static final int TYPE_NAVIGATE = 1;
    private static final int TYPE_SEARCH = 2;

    private static final int PLACE_ADDRESS = 0;
    private static final int PLACE_GEOLOCATION = 1;
    private static final int PLACE_NEARBY = 2;

    private static final int QUERY_CUSTOM = 0;

    private static final TextValidationConfig titleValidationConfig = TextValidationConfig.Builder()
            .required(StringResource.fromResourceId(R.string.validation_field_not_empty))
            .build();
    private static final TextValidationConfig latitudeValidationConfig = TextValidationConfig.Builder()
            .required(StringResource.fromResourceId(R.string.validation_field_not_empty))
            .decimal(StringResource.fromResourceId(R.string.validation_field_decimal))
            .build();
    private static final TextValidationConfig addressValidationConfig = TextValidationConfig.Builder()
            .required(StringResource.fromResourceId(R.string.validation_at_least_one_not_empty))
            .build();


    private List<TypeItem> types;
    private List<StringResource> places;
    private List<StringResource> queries;
    private Context context;
    private OnIntentCreatedListener listener;

    private EditText titleText;

    private Spinner typeSpinner;
    private Spinner placeSpinner;
    private Spinner querySpinner;

    private ViewGroup geolocationGroup;
    private ViewGroup addressGroup;
    private ViewGroup queryGroup;

    private EditText latitudeText;
    private TextView latitudeLabel;
    private EditText longitudeText;
    private TextView longitudeLabel;

    private EditText houseNumberText;
    private EditText streetText;
    private EditText cityText;
    private EditText zipCodeText;

    private EditText queryText;
    private TextView queryLabel;

    private TextView okButtonTextView;
    private TextView cancelButtonTextView;

    private final Map<TextView, Boolean> validationMap = new HashMap<>();

    public static GmapsShortcutDialogFragment newInstance(Context context, OnIntentCreatedListener onIntentCreatedListener) {
        GmapsShortcutDialogFragment f = new GmapsShortcutDialogFragment();
        f.context = context;
        f.listener = onIntentCreatedListener;
        return f;
    }

    public GmapsShortcutDialogFragment() {
        types = new ArrayList<>();
        types.add(new TypeItem(IconResource.fromResourceId(R.drawable.ic_place_black_24dp), StringResource.fromResourceId(R.string.gmaps_type_place)));
        types.add(new TypeItem(IconResource.fromResourceId(R.drawable.ic_navigation_black_24dp), StringResource.fromResourceId(R.string.gmaps_type_navigate)));
        types.add(new TypeItem(IconResource.fromResourceId(R.drawable.ic_map_black_24dp), StringResource.fromResourceId(R.string.gmaps_type_search)));

        places = new ArrayList<>();
        places.add(StringResource.fromResourceId(R.string.gmaps_place_address));
        places.add(StringResource.fromResourceId(R.string.gmaps_place_geolocation));
        places.add(StringResource.fromResourceId(R.string.gmaps_place_nearby));

        queries = new ArrayList<>();
        queries.add(StringResource.fromResourceId(R.string.gmaps_query_custom));
        queries.add(StringResource.fromResourceId(R.string.gmaps_query_gas_station));
        queries.add(StringResource.fromResourceId(R.string.gmaps_query_restaurant));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View dialogView = inflater.inflate(R.layout.fragment_dialog_shortcut_gmaps, container, false);

        geolocationGroup = (ViewGroup) dialogView.findViewById(R.id.dialog_gmaps_container_geolocation);
        addressGroup = (ViewGroup) dialogView.findViewById(R.id.dialog_gmaps_container_address);
        queryGroup = (ViewGroup) dialogView.findViewById(R.id.dialog_gmaps_container_query);

        titleText = (EditText) dialogView.findViewById(R.id.dialog_shortcut_title);
        titleText.addTextChangedListener(new SimpleTextValidator(titleText, titleValidationConfig, this));

        typeSpinner = (Spinner) dialogView.findViewById(R.id.dialog_gmaps_spinner_type);

        latitudeText = (EditText) dialogView.findViewById(R.id.dialog_gmaps_text_latitude);
        latitudeText.addTextChangedListener(new SimpleTextValidator(latitudeText, latitudeValidationConfig, this));
        latitudeLabel = (TextView) dialogView.findViewById(R.id.dialog_gmaps_label_latitude);
        longitudeText = (EditText) dialogView.findViewById(R.id.dialog_gmaps_text_longitude);
        longitudeText.addTextChangedListener(new SimpleTextValidator(longitudeText, latitudeValidationConfig, this));
        longitudeLabel = (TextView) dialogView.findViewById(R.id.dialog_gmaps_label_longitude);

        final GmapsShortcutDialogFragment inst = this;
        IndependentTextValidator addressValidator = new IndependentTextValidator() {
            @Override
            public void validate() {
                inst.validate();
            }
        };
        houseNumberText = (EditText) dialogView.findViewById(R.id.dialog_gmaps_text_houseNumber);
        houseNumberText.addTextChangedListener(addressValidator);
        streetText = (EditText) dialogView.findViewById(R.id.dialog_gmaps_text_street);
        streetText.addTextChangedListener(addressValidator);
        cityText = (EditText) dialogView.findViewById(R.id.dialog_gmaps_text_city);
        cityText.addTextChangedListener(addressValidator);
        zipCodeText = (EditText) dialogView.findViewById(R.id.dialog_gmaps_text_zipcode);
        zipCodeText.addTextChangedListener(addressValidator);

        placeSpinner = (Spinner) dialogView.findViewById(R.id.dialog_gmaps_spinner_place);
        queryText = (EditText) dialogView.findViewById(R.id.dialog_gmaps_text_query);
        queryLabel = (TextView) dialogView.findViewById(R.id.dialog_gmaps_label_query);
        querySpinner = (Spinner) dialogView.findViewById(R.id.dialog_gmaps_spinner_query);


        typeSpinner.setAdapter(new TypeAdapter());
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case TYPE_NAVIGATE: // navigate
                        queryGroup.setVisibility(View.GONE);
                        placeSpinner.setSelection(PLACE_ADDRESS);
                        break;
                    case TYPE_SEARCH:  // search
                        queryGroup.setVisibility(View.VISIBLE);
                        placeSpinner.setSelection(PLACE_NEARBY);
                        break;
                    case TYPE_DISPLAY: // display
                        queryGroup.setVisibility(View.GONE);
                        placeSpinner.setSelection(PLACE_ADDRESS);
                        break;
                    default:
                        throw new AssertionError("Unknown item selected: " + position);
                }
                validate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        placeSpinner.setAdapter(new StringListAdapter(context, places));
        placeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case PLACE_NEARBY: // nearby
                        addressGroup.setVisibility(View.GONE);
                        geolocationGroup.setVisibility(View.VISIBLE);
                        latitudeText.setText("0");
                        latitudeText.setEnabled(false);
                        latitudeLabel.setEnabled(false);
                        longitudeText.setText("0");
                        longitudeText.setEnabled(false);
                        longitudeLabel.setEnabled(false);
                        break;
                    case PLACE_ADDRESS: // address
                        addressGroup.setVisibility(View.VISIBLE);
                        geolocationGroup.setVisibility(View.GONE);
                        break;
                    case PLACE_GEOLOCATION: // geolocation
                        addressGroup.setVisibility(View.GONE);
                        geolocationGroup.setVisibility(View.VISIBLE);
                        latitudeText.setText("");
                        latitudeText.setEnabled(true);
                        latitudeLabel.setEnabled(true);
                        longitudeText.setText("");
                        longitudeText.setEnabled(true);
                        longitudeLabel.setEnabled(true);
                        break;
                    default:
                        throw new AssertionError("Unknown item selected: " + position);
                }
                validate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        querySpinner.setAdapter(new StringListAdapter(context, queries));
        querySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == QUERY_CUSTOM) {
                    queryText.setText("");
                } else {
                    StringResource strRes = queries.get(position);
                    queryText.setText(strRes.getString(context).toLowerCase());
                }
                validate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cancelButtonTextView = (TextView) dialogView.findViewById(R.id.dialog_button_cancel);
        cancelButtonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        okButtonTextView = (TextView) dialogView.findViewById(R.id.dialog_button_ok);
        okButtonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Ok button clicked");
                if (validate()) {
                    TypeItem typeItem = types.get(typeSpinner.getSelectedItemPosition());
                    listener.onIntentCreated(typeItem.icon, StringResource.fromString(titleText.getText().toString()), createIntent());
                    dismiss();
                }
            }
        });
        typeSpinner.setSelection(TYPE_NAVIGATE);
        querySpinner.setSelection(QUERY_CUSTOM);
        validate();
        return dialogView;
    }

    @Override
    public void onValid(TextView validTextView) {
        Log.d(TAG, "onValid called: " + validTextView.getText());
        validationMap.put(validTextView, true);
        validate();
    }

    @Override
    public void onInvalid(TextView invalidTextView) {
        Log.d(TAG, "onInvalid called: " + invalidTextView.getText());
        validationMap.put(invalidTextView, false);
        validate();
    }

    private boolean validate() {
        Log.d(TAG, "Validating");
        // if called before initialization, return false
        if (placeSpinner == null) {
            if (okButtonTextView != null) {
                okButtonTextView.setEnabled(false);
            }
            return false;
        }

        boolean isValid = true;

        //general validation
        if (!validationMap.get(titleText)) {
            isValid = false;
        }

        // address validation
        if (placeSpinner.getSelectedItemPosition() == PLACE_ADDRESS) {
            // at least city or zipcode must be filled
            if (!addressValidationConfig.isValid(cityText.getText().toString())
                    && !addressValidationConfig.isValid(zipCodeText.getText().toString())) {
                Log.d(TAG, "City and Zipcode invalid");
                cityText.setError(addressValidationConfig.getErrorMessage().getString());
                zipCodeText.setError(addressValidationConfig.getErrorMessage().getString());
                isValid = false;
            } else {
                Log.d(TAG, "City and Zipcode valid");
                cityText.setError(null);
                zipCodeText.setError(null);
            }
            // geolocation validation
        } else {
            // latitude and longitude must both be valid
            if (!validationMap.get(latitudeText)
                    || !validationMap.get(longitudeText)) {
                isValid = false;
            }
        }

        okButtonTextView.setEnabled(isValid);

        Log.d(TAG, "Validation ended with result: " + isValid);
        return isValid;
    }

    private Intent createIntent() {
        Log.d(TAG, "Creating intent");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage("com.google.android.apps.maps");
        String q = "";
        String geo = "0,0";
        switch (placeSpinner.getSelectedItemPosition()) {
            case PLACE_ADDRESS:
                if (houseNumberText.getText().length() > 0) {
                    q += " " + houseNumberText.getText();
                }
                if (streetText.getText().length() > 0) {
                    q += " " + streetText.getText();
                }
                if (cityText.getText().length() > 0) {
                    q += " " + cityText.getText();
                }
                if (zipCodeText.getText().length() > 0) {
                    q += " " + zipCodeText.getText();
                }
                break;
            case PLACE_GEOLOCATION:
            case PLACE_NEARBY:
                geo = latitudeText.getText() + "," + longitudeText.getText();
                break;
        }
        Uri uri = null;
        switch (typeSpinner.getSelectedItemPosition()) {
            case TYPE_DISPLAY:
                uri = Uri.parse("geo:" + geo + "?q=" + Uri.encode(q));
                break;
            case TYPE_SEARCH:
                q += " " + queryText.getText();
                uri = Uri.parse("geo:" + geo + "?q=" + Uri.encode(q));
                break;
            case TYPE_NAVIGATE:
                if (q.isEmpty()) {
                    uri = Uri.parse("google.navigation:q=" + geo);
                } else {
                    uri = Uri.parse("google.navigation:q=" + q);
                }
                break;
        }
        intent.setData(uri);
        return intent;
    }

    private class TypeAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return types.size();
        }

        @Override
        public Object getItem(int position) {
            return types.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_text_icon, null);
            }
            final TextIconListItemView itemView = (TextIconListItemView) convertView;
            final TypeItem typeItem = (TypeItem) getItem(position);
            itemView.setText(typeItem.text);
            itemView.setIcon(typeItem.icon);
            return convertView;
        }
    }

    private static class TypeItem {
        public final IconResource icon;
        public final StringResource text;

        public TypeItem(IconResource icon, StringResource text) {
            this.icon = icon;
            this.text = text;
        }
    }

    public interface OnIntentCreatedListener {
        void onIntentCreated(IconResource icon, StringResource title, Intent intent);
    }
}
