package campuscreatures.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import campuscreatures.profile.UserProfile;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment {
	
	ImageView profilePic;

	View rootView;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        setUserInfo();
        
        
        return rootView;
	    
	}
	
	private FileInputStream openFileInput(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void setUserInfo() {
		
		thumbSprites sprites = new thumbSprites(getActivity());
		UserProfile tempProfile = new UserProfile(getActivity());
		
		TextView firstName = (TextView) rootView.findViewById(R.id.textView1);
		TextView lastName = (TextView) rootView.findViewById(R.id.textView2);
		TextView userName = (TextView) rootView.findViewById(R.id.textView3);
		
		ImageButton creature1 = (ImageButton) rootView.findViewById(R.id.imageButton1);
		ImageButton creature2 = (ImageButton) rootView.findViewById(R.id.imageButton2);
		ImageButton creature3 = (ImageButton) rootView.findViewById(R.id.imageButton3);
		ImageButton creature4 = (ImageButton) rootView.findViewById(R.id.imageButton4);
		ImageButton creature5 = (ImageButton) rootView.findViewById(R.id.imageButton5);
		ImageButton creature6 = (ImageButton) rootView.findViewById(R.id.imageButton6);
		
		creature1.setImageDrawable(sprites.getthumbSprite(tempProfile.getParty().getPartyMember(0).getTitle()));
		creature2.setImageDrawable(sprites.getthumbSprite(tempProfile.getParty().getPartyMember(1).getTitle()));
		creature3.setImageDrawable(sprites.getthumbSprite(tempProfile.getParty().getPartyMember(2).getTitle()));
		creature4.setImageDrawable(sprites.getthumbSprite(tempProfile.getParty().getPartyMember(3).getTitle()));
		//creature5.setImageDrawable(sprites.getthumbSprite(tempProfile.getParty().getPartyMember(4).getTitle()));
		//creature6.setImageDrawable(sprites.getthumbSprite(tempProfile.getParty().getPartyMember(5).getTitle()));
		
		UserProfile profile = loadProfile(rootView.getContext());
		
		System.out.println(profile.getFirstName());
		firstName.setText(profile.getFirstName());
		lastName.setText(profile.getLastName());
		userName.setText(profile.getUserName());
	}
	
	public UserProfile loadProfile(Context context) {
		try {
			FileInputStream fis;
			fis = rootView.getContext().openFileInput("userProfile");
			System.out.println(fis);
			try {
				System.out.println("here A");
				ObjectInputStream is = new ObjectInputStream(fis);
				System.out.println("here A.1");
				UserProfile tempProf = (UserProfile) is.readObject();
				System.out.println("here A.2");
				is.close();
				System.out.println("here B");
				return tempProf;
			} catch (StreamCorruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("here C");
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("here D");
				e.printStackTrace();
				return null;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("here E");
				e.printStackTrace();
				return null;
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}
}