import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class client {
    public class MainActivity extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            db = FirebaseFirestore.getInstance();
            addData();
        }


        private void addData() {
            //Create a new user with a first and last name

        }

    }
