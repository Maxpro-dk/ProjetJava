package com.example.goshop.utility;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class EditError {

        List<Boolean> list;

        public EditError() {
            this.list = new ArrayList<>();
        }
        public void isEmpty(TextInputEditText editText, String error){
            if (editText.getText().toString().isEmpty()){
                editText.setError(error);
                list.add(false);
            }

        }
        public boolean isInputValide(){
            return list.isEmpty();
        }

}
