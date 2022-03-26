package com.example.customnavigationdrawer.fragment;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.customnavigationdrawer.R;
import com.example.customnavigationdrawer.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HistoryFragment extends Fragment {

    View mView;

    FirebaseDatabase database;
    DatabaseReference ref;
    User user;
    TextView txtTime;
    BluetoothDevice device;
    //    ArrayList<String> list;
//   // ArrayList<String> listTemp;
//    ArrayAdapter<String> adapter;
    int sum = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_history, container, false);


        String DeviceID = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("User/"+DeviceID+"/quet");

//        long cutoff = new Date().getTime() - TimeUnit.MILLISECONDS.convert(20, TimeUnit.SECONDS);
//        Query oldBug = ref.orderByChild("timestamp").endAt(cutoff);
//        oldBug.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {
//                    itemSnapshot.getRef().removeValue();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                throw databaseError.toException();
//            }
//        });


        user = new User();
        TextView tvSum = mView.findViewById(R.id.tv_sum);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    sum = (int) snapshot.getChildrenCount();
                    tvSum.setText("Tổng số lượt tiếp xúc: " + Integer.toString(sum) + " người");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return mView;
    }
}
