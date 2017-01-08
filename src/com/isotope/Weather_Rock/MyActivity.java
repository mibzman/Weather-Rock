package com.isotope.Weather_Rock;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    String rockCondition;
    String rockCondition2;
    String condition2Length;
    double tempKelvin;
    double tempCelsius;
    double tempFahrenheit;
    SharedPreferences Rockey_data;
    public static String filename = "first";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       weatherGetter();
       tempDescribesetter();

    }
    public void tempDescribesetter() {      // Sets the initial text descriptions for temp.  TODO: make changeable by the user
        Rockey_data = getSharedPreferences(filename, 0);
        Boolean check = Rockey_data.getBoolean("hasDone", false);
        if (check) {
            //do nothing
        }else {
            SharedPreferences.Editor editor = Rockey_data.edit();
            editor.putString("one", "Bloody Cold");
            editor.putString("two", "Hella Cold");
            editor.putString("three", "Super Duper Cold");
            editor.putString("four", "Super Cold");
            editor.putString("five", "Really Cold");
            editor.putString("six", "Very Cold");
            editor.putString("seven", "Pretty Cold");
            editor.putString("eight", "Cold");
            editor.putString("nine", "Chilly");
            editor.putString("ten", "Cool");
            editor.putString("eleven", "");
            editor.putString("twelve", "Warm");
            editor.putString("thirteen", "Hot");
            editor.putString("fourteen", "Very Hot");
            editor.putString("fifteen", "Hella Hot");
            editor.putString("sixteen", "Bloody Hot");
            editor.commit();
            }
    }
    public void weatherGetter() {      // gets the currenr weather for Lakewood from openweathermap.  TODO: make settable to any location and/or current location
        JSONObject json = null;
        String str = "";
        HttpResponse response;
        HttpClient myClient = new DefaultHttpClient();
        HttpPost myConnection = new HttpPost("http://api.openweathermap.org/data/2.5/weather?q=44322");

        try {
            response = myClient.execute(myConnection);
            str = EntityUtils.toString(response.getEntity(), "UTF-8");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {     //gets temprature in kelvin
            JSONObject jObject = new JSONObject(str);
            //JSONObject weatherObject = jObject.getJSONObject("weather");
            JSONArray weatherArray = jObject.getJSONArray("weather");
            JSONObject alsoWeatherObject = weatherArray.getJSONObject(0);


            String Idee = (alsoWeatherObject.getString("id"));
            int id = Integer.parseInt(Idee);
            myidSetter(id);
            JSONObject tempObject = jObject.getJSONObject("main");
            String temp = (tempObject.getString("temp")) ;
            tempKelvin = Double.parseDouble(temp);
            tempConverter(tempKelvin);
             TextView textView3 = (TextView) findViewById(R.id.textView2);
            //textView3.setText(temp);

            //url.setText(json.getString("url"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void tempConverter(double temp3){
         tempCelsius = temp3 - 273.15;

        DecimalFormat df = new DecimalFormat("#.##");
        tempCelsius = Double.valueOf(df.format(tempCelsius));
         tempFahrenheit = (((tempCelsius * 9) / 5) + 32);
        DecimalFormat df2 = new DecimalFormat("#.##");
        tempFahrenheit = Double.valueOf(df.format(tempFahrenheit));
         TextView textView3 = (TextView) findViewById(R.id.textView2);
        //textView3.setText(" " + tempFahrenheit + " ");
        tempSetter();
    }
    public void tempSetter() {
        Rockey_data = getSharedPreferences(filename, 0);
       if (tempFahrenheit <= -31){
            tempTextSetter(Rockey_data.getString("one", "default"));
       }else if(tempFahrenheit <= -21){
           tempTextSetter(Rockey_data.getString("two", "default"));
       }else if(tempFahrenheit <= -11){
           tempTextSetter(Rockey_data.getString("three", "default"));
       }else if(tempFahrenheit <= -1){
           tempTextSetter(Rockey_data.getString("four", "default"));
       }else if(tempFahrenheit <= 9){
           tempTextSetter(Rockey_data.getString("five", "default"));
       }else if(tempFahrenheit <= 19){
           tempTextSetter(Rockey_data.getString("six", "default"));
       }else if(tempFahrenheit <= 29){
           tempTextSetter(Rockey_data.getString("seven", "default"));
       }else if(tempFahrenheit <= 39){
           tempTextSetter(Rockey_data.getString("eight", "default"));
       }else if(tempFahrenheit <= 49){
           tempTextSetter(Rockey_data.getString("nine", "default"));
       }else if(tempFahrenheit <= 59){
           tempTextSetter(Rockey_data.getString("ten", "default"));
       }else if(tempFahrenheit <= 69){
           tempTextSetter(Rockey_data.getString("eleven", "default"));
       }else if(tempFahrenheit <= 79){
           tempTextSetter(Rockey_data.getString("twelve", "default"));
       }else if(tempFahrenheit <= 89){
           tempTextSetter(Rockey_data.getString("thirteen", "default"));
       }else if(tempFahrenheit <= 99){
            tempTextSetter(Rockey_data.getString("fourteen", "default"));
        }else if(tempFahrenheit <= 109){
           tempTextSetter(Rockey_data.getString("fifteen", "default"));
       }else if(tempFahrenheit >109){
           tempTextSetter(Rockey_data.getString("sixteen", "default"));
       }
    }
    public void tempTextSetter(String inputstring){
        rockCondition = inputstring;
    }
    public void myidSetter(int id) {
        int myId;
        switch (id) {                                    //seems unnessesary but helps simplify duplicates and minor differences
            case 200:
                myId = 1;
                break;
            case 201:
                myId = 2;
                break;
            case 202:
                myId = 3;
                break;
            case 210:
                myId = 4;
                break;
            case 211:
                myId = 5;
                break;
            case 212:
                myId = 6;
                break;
            case 221:
                myId = 5;
                break;
            case 230:
                myId = 7;
                break;
            case 231:
                myId = 8;
                break;
            case 232:
                myId = 9;
                break;
            case 300:
                myId = 10;
                break;
            case 301:
                myId = 11;
                break;
            case 302:
                myId = 12;
                break;
            case 310:
                myId = 10;
                break;
            case 311:
                myId = 11;
                break;
            case 312:
                myId = 12;
                break;
            case 313:
                myId = 11;
                break;
            case 314:
                myId = 12;
                break;
            case 321:
                myId = 10;
                break;
            case 500:
                myId = 13;
                break;
            case 501:
                myId = 14;
                break;
            case 502:
                myId = 15;
                break;
            case 503:
                myId = 16;
                break;
            case 504:
                myId = 17;
            case 511:
                myId = 18;
                break;
            case 520:
                myId = 13;
                break;
            case 521:
                myId = 14;
                break;
            case 522:
                myId = 15;
                break;
            case 531:
                myId = 13;
                break;
            case 600:
                myId = 19;
                break;
            case 601:
                myId = 20;
                break;
            case 602:
                myId = 21;
                break;
            case 611:
                myId = 22;
                break;
            case 612:
                myId = 22;
                break;
            case 615:
                myId = 22;
                break;
            case 616:
                myId = 22;
                break;
            case 620:
                myId = 19;
                break;
            case 621:
                myId = 20;
                break;
            case 622:
                myId = 21;
                break;
            case 701:
                myId = 23;
                break;
            case 711:
                myId = 23;
                break;
            case 721:
                myId = 23;
                break;
            case 731:
                myId = 23;
                break;
            case 741:
                myId = 23;
                break;
            case 751:
                myId = 23;
                break;
            case 761:
                myId = 23;
                break;
            case 762:
                myId = 23;
                break;
            case 771:
                myId = 24;
                break;
            case 781:
                myId = 25;
                break;
            case 800:
                myId = 26;
                break;
            case 801:
                myId = 27;
                break;
            case 802:
                myId = 27;
                break;
            case 803:
                myId = 27;
                break;
            case 804:
                myId = 27;
                break;
            case 900:
                myId = 25;
                break;
            case 901:
                myId = 28;
                break;
            case 902:
                myId = 29;
                break;
            case 903:
                myId = 30;
                break;
            case 904:
                myId = 31;
                break;
            case 905:
                myId = 32;
                break;
            case 906:
                myId = 33;
                break;
            case 950:
                myId = 34;
                break;
            case 951:
                myId = 35;
                break;
            case 952:
                myId = 36;
                break;
            case 953:
                myId = 37;
                break;
            case 954:
                myId = 38;
                break;
            case 955:
                myId = 38;
                break;
            case 956:
                myId = 39;
                break;
            case 957:
                myId = 40;
                break;
            case 958:
                myId = 41;
                break;
            case 959:
                myId = 42;
                break;
            case 960:
                myId = 43;
                break;
            case 961:
                myId = 44;
                break;
            case 962:
                myId = 29;
                break;
            default:
                myId = 45;
                break;

        }
        rockDescribe(myId);
    }
    public void rockDescribe(int myId) {
        switch (myId){
            case 1:
                rockCondition2 = "Slightly Wet and Electrified";
                condition2Length = "two";      // helps describe how the description is worded to prevent gramatical errors
                break;
            case 2:
                rockCondition2 = "Wet and Electrified";
                condition2Length = "two";
                break;
            case 3:
                rockCondition2 = " Very Wet and Electrified";
                condition2Length = "two";
                break;
            case 4:
                rockCondition2 = "Slightly Electrified";
                condition2Length = "one";
                break;
            case 5:
                rockCondition2 = "Electrified";
                condition2Length = "one";
                break;
            case 6:
                rockCondition2 = "Very Electrified";
                condition2Length = "one";
                break;
            case 7:
                rockCondition2 = "Slightly Moist and Electrified";
                condition2Length = "two";
                break;
            case 8:
                rockCondition2 = "Moist and Electrified";
                condition2Length = "two";
                break;
            case 9:
                rockCondition2 = "Very Moist and Electrified";
                condition2Length = "two";
                break;
            case 10:
                rockCondition2 = "Slightly Moist";
                condition2Length = "one";
                break;
            case 11:
                rockCondition2 = "Moist";
                condition2Length = "two";
                break;
            case 12:
                rockCondition2 = "Very Moist";
                condition2Length = "one";
                break;
            case 13:
                rockCondition2 = "Barley Wet";
                condition2Length = "one";
                break;
            case 14:
                rockCondition2 = "Slightly Wet";
                condition2Length = "one";
                break;
            case 15:
                rockCondition2 = "Wet";
                condition2Length = "one";
                break;
            case 16:
                rockCondition2 = "Very Wet";
                condition2Length = "one";
                break;
            case 17:
                rockCondition2 = "Extremely Wet";
                condition2Length = "one";
                break;
            case 18:
                rockCondition2 = "Frozen and Wet";
                condition2Length = "two";
                break;
            case 19:
                rockCondition2 = "Slightly Frozen";
                condition2Length = "one";
                break;
            case 20:
                rockCondition2 = "Frozen";
                condition2Length = "one";
                break;
            case 21:
                rockCondition2 = "Very Frozen";
                condition2Length = "one";
                break;
            case 22:
                rockCondition2 = "Wet and Frozen";
                condition2Length = "two";
                break;
            case 23:
                rockCondition2 = "Hard to See";
                condition2Length = "one";
                break;
            case 24:
                rockCondition2 = "Moving and Wet";
                condition2Length = "two";
                break;
            case 25:
                rockCondition2 = "Being Sucked up into the Sky.  Run!";
                condition2Length = "one";
                break;
            case 26:
                rockCondition2 = "Bright"; // TODO: improve this description
                condition2Length = "one";
                break;
            case 27:
                rockCondition2 = "Dark";
                condition2Length = "one";
                break;
            case 28:
                rockCondition2 = "Flying Around and Getting Drenched";
                condition2Length = "two";
                break;
            case 29:
                rockCondition2 ="Missing.  Run!";
                condition2Length = "two";
                break;
            case 30:
                rockCondition2 = "Cold";
                condition2Length = "cold";
                break;
            case 31:
                rockCondition2 = "Hot";
                condition2Length = "hot";
                break;
            case 32:
                rockCondition2 = "Moving";
                condition2Length = "one";
                break;
            case 33:
                rockCondition2 = "Dented";
                condition2Length = "one";
                break;
            case 34:
                rockCondition2 = "Confused?";
                condition2Length = "one";
                break;
            case 35:
                rockCondition2 = "Still";
                condition2Length = "one";
                break;
            case 36:
                rockCondition2 = "Barley Moving";
                condition2Length = "one";
                break;
            case 37:
                rockCondition2 = "Slightly Moving";
                condition2Length = "one";
                break;
            case 38:
                rockCondition2 = "Moving";
                condition2Length = "one";
                break;
            case 39:
                rockCondition2 = "Moving Lots";
                condition2Length = "one";
                break;
            case 40:
                rockCondition2 = "About to Take Flight";
                condition2Length = "one";
                break;
            case 41:
                rockCondition2 = "Taking Flight";
                condition2Length = "one";
                break;
            case 42:
                rockCondition2 = "Gone With the Wind";
                condition2Length = "one";
                break;
            case 43:
                rockCondition2 = "Wet and Moving";
                condition2Length = "two";
                break;
            case 44:
                rockCondition2 ="Very Wet and Very Moving";
                condition2Length = "two";
                break;
            case 45:
                rockCondition2 = "Confused";
                condition2Length = "one";
                break;
            default:
                rockCondition2 = "Confused";
                condition2Length = "one";
                break;
        }
        TextView textView = (TextView) findViewById(R.id.textView2);
        //textView.setText(" " + myId + " ");
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText(rockCondition2);
    }
}
