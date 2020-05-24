package se.mau.ai0405.p3;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass that contains an {@link ImageView} that is
 * shown when the player answered a question correctly.
 *
 * @author Hampus Hansson
 */
public class ImageFragment extends Fragment {
    private GameController controller;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);

        initializeComponents(view);
        view.setOnTouchListener(controller);

        return view;
    }

    /**
     * Initializes the components in the fragment.
     *
     * @param view The view in which the components should be initialized
     */
    private void initializeComponents(View view) {
        imageView = view.findViewById(R.id.imageView);
    }

    /**
     * Sets the image of the {@link ImageView}.
     *
     * @param drawable The image
     */
    public void setImage(Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

    /**
     * Sets the controller of the fragment.
     *
     * @param controller The controller
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }
}

