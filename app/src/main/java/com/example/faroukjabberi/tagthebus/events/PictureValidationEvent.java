package com.example.faroukjabberi.tagthebus.events;

/**
 * Created by farouk.jabberi on 12/12/2017.
 */

public class PictureValidationEvent {
    private  boolean validation;

    public PictureValidationEvent(boolean validation) {
        this.validation = validation;
    }

    public boolean isValidation() {
        return validation;
    }
}
