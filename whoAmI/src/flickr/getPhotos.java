package flickr;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import model.Model;
import model.PicDAO;

import org.genericdao.RollbackException;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.xml.sax.SAXException;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.Size;
import com.flickr4java.flickr.photosets.Photoset;
import com.flickr4java.flickr.photosets.PhotosetsInterface;
import com.flickr4java.flickr.util.AuthStore;
import com.flickr4java.flickr.util.FileAuthStore;

import databean.Pic;

public class getPhotos {
	private final String nsid;

	private final Flickr flickr;

	private AuthStore authStore;

	private PicDAO picDAO;

	public getPhotos(String apiKey, String nsid, String sharedSecret,
			Model model) {
		flickr = new Flickr(apiKey, sharedSecret, new REST());
		this.nsid = nsid;
		this.picDAO = model.getPicDAO();
	}

	private void authorize() throws IOException, SAXException, FlickrException {
		AuthInterface authInterface = flickr.getAuthInterface();
		Token accessToken = authInterface.getRequestToken();

		String url = authInterface.getAuthorizationUrl(accessToken,
				Permission.READ);
		System.out.println("Follow this URL to authorise yourself on Flickr");
		System.out.println(url);
		System.out.println("Paste in the token it gives you:");
		System.out.print(">>");

		String tokenKey = new Scanner(System.in).nextLine();

		Token requestToken = authInterface.getAccessToken(accessToken,
				new Verifier(tokenKey));

		Auth auth = authInterface.checkToken(requestToken);
		RequestContext.getRequestContext().setAuth(auth);
		this.authStore.store(auth);
		System.out
				.println("Thanks.  You probably will not have to do this every time.  Now starting backup.");
	}

	public void getAllPhotos() {
		RequestContext rc = RequestContext.getRequestContext();

		if (this.authStore != null) {
			Auth auth = this.authStore.retrieve(this.nsid);
			if (auth == null) {
				try {
					this.authorize();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FlickrException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				rc.setAuth(auth);
			}
		}

		PhotosetsInterface pi = flickr.getPhotosetsInterface();
		PhotosInterface photoInt = flickr.getPhotosInterface();
		Map<String, Collection> allPhotos = new HashMap<String, Collection>();

		Iterator sets;
		try {
			sets = pi.getList(this.nsid).getPhotosets().iterator();
			while (sets.hasNext()) {
				Photoset set = (Photoset) sets.next();
				PhotoList photos;
				photos = pi.getPhotos(set.getId(), 500, 1);
				allPhotos.put(set.getTitle(), photos);
			}
			
			Iterator allIter = allPhotos.keySet().iterator();

			while (allIter.hasNext()) {
				String setTitle = (String) allIter.next();
				System.out.println(setTitle);
				
				if (setTitle.equals("whoami")) {
					System.out.println("Now in whoami albumn");
					Collection currentSet = allPhotos.get(setTitle);
					Iterator setIterator = currentSet.iterator();

					while (setIterator.hasNext()) {
						Photo p = (Photo) setIterator.next();
						Pic pic = picDAO.getPicByURL(p.getMediumUrl());
						if (pic == null) {
							Pic newpic = new Pic();
							newpic.setTitle(p.getTitle().trim());
							newpic.setUrl(p.getMediumUrl());
							picDAO.createAutoIncrement(newpic);
						}
					}
				}
			}

		} catch (FlickrException | RollbackException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	// public static void main(String[] args) throws Exception {
	//
	// String a_api_key = "4222c97abc1c18a7a314993bcb28993e";
	// String a_nsid = "131094040@N04";
	// String a_shared_secret = "7858d27d5d8971a6";
	// getPhotos gp = new getPhotos(a_api_key, a_nsid, a_shared_secret,
	// new File(System.getProperty("user.home") + File.separatorChar
	// + ".flickrAuth"));
	// gp.getAllPhotos(new File(a_output_dir));
	// }
}
