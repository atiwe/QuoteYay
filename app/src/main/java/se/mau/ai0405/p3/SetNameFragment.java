package se.mau.ai0405.p3;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass for setting the name of the user.
 *
 * @author Hampus Hansson
 */
public class SetNameFragment extends Fragment {
    private Controller controller;
    private Button buttonSave;
    private EditText fieldName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_name, container, false);
        
        initializeComponents(view);
        registerListeners();
        
        return view;
    }

    /**
     * Initializes the components in the fragment.
     *
     * @param view The view in which the components should be initialized
     */
    private void initializeComponents(View view) {
        buttonSave = view.findViewById(R.id.buttonSave);
        fieldName = view.findViewById(R.id.fieldName);
    }

    /**
     * Registers the listener of the button.
     */
    private void registerListeners() {
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager manager = (InputMethodManager) getActivity().
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(fieldName.getWindowToken(), 0);

                String name = fieldName.getText().toString();
                controller.buttonSaveNameClicked(name);
            }
        });
    }

    /**
     * Sets the controller of the fragment.
     *
     * @param controller The controller
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
