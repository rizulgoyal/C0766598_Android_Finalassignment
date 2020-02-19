package com.example.c0766598_android_finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.c0766598_android_finalassignment.RoomDatabase.PersonDB;

public class AddPersonActivity extends AppCompatActivity {

    EditText fname, lname, address, phone;
    Button addPerson;

    Person editPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_person );

        fname = findViewById( R.id.text_fname );
        lname = findViewById( R.id.text_lname );
        address = findViewById( R.id.text_address );
        phone = findViewById( R.id.text_phone );
        addPerson = findViewById( R.id.button_submit );
        editPerson = getIntent().getParcelableExtra( "person" );


        addPerson.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fnameString = fname.getText().toString();
                String lnameString = lname.getText().toString();
                String addressString = address.getText().toString();
                String phoneString = phone.getText().toString();

                PersonDB personDB = PersonDB.getInstance( v.getContext() );


                if (editPerson != null)
                {
                    editPerson.setFirstname( fnameString );
                    editPerson.setLastname( lnameString );
                    editPerson.setAddress( addressString );
                    editPerson.setPhone( phoneString );
                    personDB.daoObjct().update( editPerson );

                }
                else {

                    Person person = new Person( fnameString, lnameString, addressString, phoneString );


                    personDB.daoObjct().insert( person );
                }

                finish();
            }
        } );

        if (editPerson != null)
        {
            fname.setText( editPerson.getFirstname() );
            lname.setText( editPerson.getLastname() );
            address.setText( editPerson.getAddress() );
            phone.setText( editPerson.getPhone() );

        }


    }
}
