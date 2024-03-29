package com.theshmittahapp.android.views.Fragments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.theshmittahapp.android.models.Produce;
import com.theshmittahapp.android.R;
import com.theshmittahapp.android.views.Activities.ProduceDetailsActivity;

public class ProduceFragment extends Fragment{
	
	private String url = "http://www.mymakolet.com";
	
	private View mRootView;
	private ListView mListView;
	
	public ProduceFragment() { }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		// inflate view
		mRootView = inflater.inflate(R.layout.fragment_produce, container, false);
		ImageView ad = (ImageView) mRootView.findViewById(R.id.ad);
		
		// no ad in landscape so check for null first
		if (ad!=null) {
			ad.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Uri webpage = Uri.parse(url);
				    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
				    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
				    startActivity(Intent.createChooser(intent, ""));				
				}
			});
		}
		// create list of Produce objects
		List<Produce> allProduce = createProduceArrayList();
		
		// add list to the listview adapter
		addListToAdapter(allProduce);

		return mRootView;
	}
	
	
	
	private void addListToAdapter(List<Produce> allProd) {
		final List<Produce> allProduce = allProd;
		String[] allProduceNamesArray = getResources().getStringArray(R.array.fruits_and_veggies);
		
		List<String> allProduceNamesList = new ArrayList<String>(Arrays.asList(allProduceNamesArray));
		
		ArrayAdapter<String> produceAdapter =
                new ArrayAdapter<String>(
                    getActivity(), // The current context (this activity)
                    R.layout.produce_list_item, // The name of the layout ID.
                    R.id.list_item_produce_textview, // The ID of the textview to populate.
                    allProduceNamesList);
		
		mListView = (ListView) mRootView.findViewById(R.id.producelist);
		mListView.setAdapter(produceAdapter);

		/* Add Click Listener */

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(),
						ProduceDetailsActivity.class);
				Produce produce = allProduce.get(position);
				intent.putExtra(ProduceDetailsActivity.PRODUCE, produce);
				startActivity(intent);
			}
		});
	}

	private List<Produce> createProduceArrayList() {
		String[] allProduceNames = getResources().getStringArray(R.array.fruits_and_veggies);
		String[] allProduceFrom = getResources().getStringArray(R.array.from_date);
		String[] allProduceTo = getResources().getStringArray(R.array.to_date);
		String[] allProducePrepare = getResources().getStringArray(R.array.prepared);
		String[] allProducePeeled = getResources().getStringArray(R.array.peeled);
		String[] allProducePits = getResources().getStringArray(R.array.pits);
		String[] allProducePeel = getResources().getStringArray(R.array.peel);
		String[] allProduceMashed = getResources().getStringArray(R.array.mashed);
		String[] allProduceComments = getResources().getStringArray(R.array.comments);
		
		List<Produce> allProduce = new ArrayList<Produce>();
		for (int i=0, totalProduce=allProduceNames.length; i<totalProduce; i++)
		{
			Produce produce = new Produce(
					allProduceNames[i],
					allProduceFrom[i],
					allProduceTo[i],
					allProducePrepare[i],
					allProducePeeled[i],
					allProducePits[i],
					allProducePeel[i],
					allProduceMashed[i],
					allProduceComments[i]
					);
			allProduce.add(produce);
		}
		
		return allProduce;
	}
}