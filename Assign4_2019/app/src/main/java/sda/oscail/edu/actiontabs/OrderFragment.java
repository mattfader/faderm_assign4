package sda.oscail.edu.actiontabs;


        import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
        import android.widget.Switch;
        import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;
import static android.content.Intent.EXTRA_STREAM;
        import static sda.oscail.edu.actiontabs.R.id.contentPanel;
        import static sda.oscail.edu.actiontabs.R.id.imageView;


//Adapted from code written by Colette Kirwan. DCU Open Education
//Modified by Matthew Fader for assignment 4 SDA 2019

public class OrderFragment extends Fragment
{

    //fileProvider update variables below
    Uri mPhotoURI;
    File mPhotoFile = null;
    //declare all variables, methods and classes below.
    Spinner mSpinner;
    EditText mCustomerName;
    EditText meditOptional;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 2;
    private static final String TAG = "Assign3";
    Button pressButton;
    //TextView m_textView;
    //int m_count=0;
    View.OnClickListener mOnClickListener;
    private PackageManager packageManager;


    /**
     * *
     *@author Colette Kirwan, modified by Matthew Fader
     * @since 11 12
     * @param //savedInstanceState
     * This activity colour theme was changed in the sytles.xml to dark blue, as a result the spinner black default text
     * was to low a contrast to see, therefor the colour of the text was altered and then tests in test A0005 in excel sheet.
     */
    private File createTempFile(){     // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFileName = "My_Image_" + timeStamp + "_";
        //we should get a general reference to external storage for images.
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);     //declare an image file
        File myImage = null;

        //try catch, ensure it doesn't crash if the file fails to be taken
        try     {
            //make an empty file
            myImage = File.createTempFile(imageFileName, ".jpg", storageDir);

        } catch (IOException e)     {
            String error = String.valueOf(e);
            Log.e(TAG, error);

            //toaster alert to let the user know there's an issue
            Toast toast = Toast.makeText(getActivity(), "Please try retaking your photo!", Toast.LENGTH_LONG);         toast.show();

        }     return myImage; }

    private File getExternalFilesDir(String directoryPictures) {
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View root = inflater.inflate(R.layout.orderfragment, container, false);



        //super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // This forces the activity to render in only portrait orientation.
        meditOptional = (EditText) root.findViewById(R.id.editOptional); // this field takes in address details on activity_order.xml.
/*
        meditOptional.setImeOptions(EditorInfo.IME_ACTION_DONE); //This ensures that the text it receives is or the roman keyboard or ASCII only.
        meditOptional.setRawInputType(InputType.TYPE_CLASS_TEXT);
*/
        meditOptional.setImeOptions(EditorInfo.IME_ACTION_DONE); //This ensures that the text it receives is or the roman keyboard or ASCII only.
        meditOptional.setRawInputType(InputType.TYPE_CLASS_TEXT);

        //initialise spinner using the integer array
        mSpinner = (Spinner) root.findViewById(R.id.spinner); // connects spinner widget to mSpinner variable defined above as a spinner type.
        mCustomerName = (EditText) root.findViewById(R.id.editCustomer); //parses user text from editCustomer field and stores it in mCustomerName defined as Edit Text type.

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(  getActivity(),(R.array.ui_time_entries),( R.layout.spinner_days)); // ui_time_entries is an array passing date and time into the adapter, this is used to create the label of the photo being captured.
                mSpinner.setAdapter(adapter); // assigns adapter just created to mSpinner var defined as spinner type.


    /**
     * This method is largely unchanged, its functionality is flawless once the default skd is changed to version 23.
     * no issues have been detected with the below code.
     *
     * Here is the mixing pot for the photo. This method takes the photo and applied a naming algorithm assigning a name, date and time stamp, and jpg file format.
     *
     *
     * @param
     */
        final ImageView camera = (ImageView) root.findViewById(imageView);
        camera.setOnClickListener(new View.OnClickListener() {


            private PackageManager packageManager;

            public PackageManager getPackageManager() {
               return packageManager;
            }

            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();


                //public void dispatchTakePictureIntent (View) view)
                Intent takePictureIntent;
                {
                    //start the intent.
                    takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


                    // check to see if the phone actually has a camera.
                    if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null)

                    {
                        //  first let’s call our new method to get the photofile
                        mPhotoFile = createTempFile();

                        // Continue only if the File was successfully created
                        if (mPhotoFile != null) {
                            //here we grab the Uri, (note we are using the authority it's our applicationID again)
                            mPhotoURI = FileProvider.getUriForFile(getActivity(),"sda.oscail.edu.actiontabs", mPhotoFile);


                            //take the photo replacing the file at the location.
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI);
                            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                        } else {

                            //toaster alert to let the user know the image is missing
                            Toast toast = Toast.makeText(getActivity(), "There was a problem saving please retry", Toast.LENGTH_LONG);
                            toast.show();
                        }

                    } else {


                        //toaster alert to let the user know there's an issue with the camera
                        Toast toast = Toast.makeText(getActivity(), "There seems to be an issue with your camera", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }


                /**
                 * Here is an interface made for when the user returns back from the camera app, if the image is present the toast message is shown.
                 * there is a secondary notification dialog shown upon return.
                 *
                 * @param requestCode
                 * @param resultCode
                 * @param data
                 */
                public void onActivityResult ( int requestCode, int resultCode, Intent data){

                    //also can give user a message that everything went ok
                    if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
                        //let user know that image saved
                        //I have strings in strings.xml but have hardcoded here to copy/paste to students if needed
                        //CharSequence text = "Image Taken successfully"; //I've corrected this hardcode below internationalized it.
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getActivity(), getString(R.string.image_confirm), duration); //hardcoded string is replaced!
                        toast.show();
                        //  ImageSwitcher.setImageResource([R.id.mPhotoURI]);
                        // imageView = setImageResource(mPhotoURI);
                        //or perhaps do a dialog should only use one method i.e. toast or dialog, but have both code here for demo purposes
                        //also I have strings in strings.xml but have hardcoded here to copy/paste to students if needed
                        // I have removed the hard coded strings from the below code. I've decided to leave both notifications in as its a strong reassurance without having the "Take photo for T-Shirt" picture update to reflect the change.
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle(getString(R.string.notification_title)).setMessage(getString(R.string.image_confirm)).setPositiveButton("OK", null).show();
                    }
                    //         getActivity(); ImageView image = new Intent();
                    //       image.setData(mPhotoURI);
                    //     ImageView imageView = image;
                    // ImageSwitcher.setImageResource((R.id.imageView[R.id.mPhotoURI]);
                    //ImageView.setImageResource(mPhotoURI);
                }


                /**
                 * Returns the Email Body Message.
                 * <p> Email body message is created used prescription related data inputed from user </p>
                 * This formats the message body by combining multiple variables form various locations e.g. strings.xml, text views, edit texts, ect.
                 *
                 * @return Email Body Message
                 */
                private String createOrderSummary () {
                    //this formats the email message for the message
                    String orderMessage = getString(R.string.customer_name) + " " + mCustomerName.getText().toString();
                    orderMessage += "\n" + "\n" + getString(R.string.order_message_1);
                    String optionalInstructions = meditOptional.getText().toString();
                    orderMessage += "\n" + getString(R.string.order_message_collect) + ((CharSequence) mSpinner.getSelectedItem()).toString() + " days";
                    orderMessage += "\n" + getString(R.string.delivery_details); // I relocated the optionalInstructions to place this message and details in legible order and before signature.
                    orderMessage += "\n" + optionalInstructions;
                    orderMessage += "\n" + getString(R.string.order_message_end) + "\n" + mCustomerName.getText().toString();


                    return orderMessage; // output to message body of email.
                    //update screen
                }
                {

            final Button switchy = (Switch) root.findViewById(R.id.switch1);
                    switchy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
            if (switchy !=null){
             // state in email that the order is for collection.

             // attach collection point information in email rather than delivery address
             // make method to force collection tab, and store variable information.
            }
            else{
                //state in email that order is to be delivered!
                // attach address information as expected.
            }
                }
                /**
                 * Send email pieces together the remaining details to have a fully filled in email to send the t-shirt, if customer name is not the same as the
                 * value in Regex i.e. editText field in activity_order.xml it will stop the email making process, and notify the user of the error.
                 * @param v
                 */

                {
                    Button sendbtn = (Button) root.findViewById(R.id.button);
                    sendbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //public void sendEmail(View v) {


                            //check that Name is not empty, and ask do they want to continue
                            String customerName = mCustomerName.getText().toString();
                            if (customerName.matches("")) // compares the editText field with the customerName var
                            {
                                //Toast.makeText(this, getString(R.string.customer_name_blank), Toast.LENGTH_SHORT).show(); //commented out. I prefer notification dialog here to toast.

                                /// we can also use a dialog
                                // all hardcoding removed.
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle(getString(R.string.notification_title)).setMessage(getString(R.string.customer_name_blank)).setPositiveButton("OK", null).show();

                            } else {
                                Intent intent = new Intent(Intent.ACTION_SEND);


                                intent.setType("*/*"); // the type is set the "multiple types"

                                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.to_email)}); // this inserts the scripted email address in strings.xml to the "to:" field.
                                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject)); // adds scripted subject line from strings.xml
                                intent.putExtra(Intent.EXTRA_STREAM, mPhotoURI);
                               // intent.putExtra(EXTRA_STREAM, mPhotoURI); // attaches the photo based on the photoUri location to the message
                                intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary()); // write additional information to message body i.e. address for delivery.
                                if (intent.resolveActivity(getActivity().getPackageManager()) != null) // if email intents are not complete escape, otherwise next line.
                                {
                                    startActivity(intent); // piece email together in client intent.
                                }
                            }
                        }
                    });

                }
            });
        //return root;
                    }});
    return root;}}