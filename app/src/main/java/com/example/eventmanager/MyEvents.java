package com.example.eventmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyEvents extends AppCompatActivity {
    RecyclerView recview;
    ArrayList<model> datalist;
    FirebaseFirestore fStore;
    FirebaseAuth mAuth;
    String userId;

    myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);
        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        datalist = new ArrayList<>();
        adapter = new myadapter(datalist);
        recview.setAdapter(adapter);


        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        fStore.collection("users").document(userId).collection("Registered_Events").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d:list){
                    model obj = d.toObject(model.class);
                    datalist.add(obj);
                }
                adapter.notifyDataSetChanged();
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recview);

    }

    model deletedlist = null;
    List<model> archivedList = new ArrayList<>();

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
                switch (direction){
                    case ItemTouchHelper.LEFT:
                        deletedlist = datalist.get(position);
                        datalist.remove(position);
                        adapter.notifyItemRemoved(position);
                        Snackbar.make(recview,deletedlist.toString()+", Deleted",Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                datalist.add(position,deletedlist);
                                adapter.notifyItemInserted(position);
                            }
                        }).show();
                        break;
                    case ItemTouchHelper.RIGHT:
                        model datalistname = datalist.get(position);
                        archivedList.add(datalistname);
                        datalist.remove(position);
                        adapter.notifyItemRemoved(position);
                        Snackbar.make(recview,datalistname + ", Archived",Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                archivedList.remove(archivedList.lastIndexOf(datalistname));
                                datalist.add(position,datalistname);
                                adapter.notifyItemInserted(position);
                            }
                        }).show();
                        break;
                }

        }

    };

}