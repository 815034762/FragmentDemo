package zhangtianyagn.com.cn.fragmentdem.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zhangtianyagn.com.cn.fragmentdem.R;

public class SendFragment extends Fragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.e("tag"," =================已经被实例化了33================= ");
		View view = inflater.inflate(R.layout.fragment_send, container, false);
		return view;
	}
}
