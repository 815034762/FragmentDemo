package zhangtianyagn.com.cn.fragmentdem.fragment;



import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import zhangtianyagn.com.cn.fragmentdem.OneActivity;
import zhangtianyagn.com.cn.fragmentdem.R;

public class SeeFragment extends Fragment {

	private Button mTurn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.e("tag"," =================已经被实例化了11================= ");
		View view = inflater.inflate(R.layout.fragment_see, container, false);
		mTurn = (Button) view.findViewById(R.id.btn_turn);
		mTurn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), OneActivity.class);
				startActivity(intent);
			}
		});
		return view;
	}
}
