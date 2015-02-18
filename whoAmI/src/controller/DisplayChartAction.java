//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CommentDAO;
import model.Model;
import model.PicDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import twitter4j.Twitter;
import databean.Comment;
import databean.Star;
import databean.Pic;
import databean.questionBean;
import formbean.ListForm;

public class DisplayChartAction extends Action {
	private FormBeanFactory<ListForm> formBeanFactory = FormBeanFactory
			.getInstance(ListForm.class);

	private CommentDAO CommentDAO;
	private PicDAO picDAO;

	public DisplayChartAction(Model model) {
		picDAO = model.getPicDAO();
		CommentDAO = model.getCommentDAO();
	}

	public String getName() {
		return "displayChart.do";
	}

	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		questionBean ques = (questionBean) request.getSession(false)
				.getAttribute("question");

		try {
			Pic pic = (Pic) picDAO.getPicByURL(ques.getUrl());
			// Comment[] CommentList =
			// CommentDAO.getComments(pic.getTitle().trim().toLowerCase());
			String cname = pic.getTitle().trim().toLowerCase()
					.replaceAll("\\s", "");
			System.out.println("pic.getTitle().trim().toLowerCase()"
					+ pic.getTitle().trim().toLowerCase());
			System.out.println("cname" + cname);

			Comment[] CommentList = CommentDAO.getComments(cname);
			if (CommentList == null) {
				CommentList = CommentDAO.getComments("katyperry");
			}
			System.out.println("CommentList.length" + CommentList.length);

			if (CommentList.length == 0 || CommentList == null) {
				return "gameplay.do";
			}
			if (CommentList.length != 0 || CommentList != null) {

				request.setAttribute("CommentList", CommentList);
			}

			String name = (String) request.getSession()
					.getAttribute("starname");
			System.out.println("starname : " + name);
			Star star = new Star();
			setupStar(star, name);
			System.out.print(star.getName() + "set up!");
			request.setAttribute("star", star);

			// HttpSession session = request.getSession();
			// session.setAttribute("CommentList", CommentList);

			return "DisplayCharts.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}

	private void setupStar(Star star, String name) {
		System.out.println("star name = " + name);
		if (name.equals("Katy Perry")) {
			star.setName("Katy Perry");
			star.setTwitterUrl("https://twitter.com/katyperry");
			star.setTwitterName("KATY PERRY");
			star.setScreenName("@katyperry");
			star.setProfileImgURL("https://pbs.twimg.com/profile_images/423542935368380416/ryEG2fNO_400x400.jpeg");
			star.setDescription("Katheryn Elizabeth Hudson, better known by her stage name Katy Perry, is an American singer and songwriter. She had limited exposure to secular music during her childhood and pursued a career in gospel music as a teenager.");
		}

		if (name.equals("Taylor Swift")) {
			star.setName("Taylor Swift");
			star.setTwitterUrl("https://twitter.com/taylorswift13");
			star.setTwitterName("Taylor Swift");
			star.setScreenName("@taylorswift13");
			star.setProfileImgURL("http://i.forbesimg.com/media/lists/people/taylor-swift_416x416.jpg");
			star.setDescription("Taylor Alison Swift is an American singer-songwriter. Raised in Wyomissing, Pennsylvania, Swift moved to Nashville, Tennessee, at the age of 14 to pursue a career in country music.");
		}

		if (name.equals("Johnny Depp")) {
			star.setName("Johnny Depp");
			star.setTwitterUrl("https://twitter.com/John_C_Depp");
			star.setTwitterName("Jonny Depp");
			star.setScreenName("@John_C_Depp");
			star.setDescription("John Christopher \"Johnny\" Depp II is an American actor, producer, and musician. He has won the Golden Globe Award and Screen Actors Guild award for Best Actor.");
		}

		if (name.equals("Carey Mulligan")) {
			star.setName("Carey Mulligan");
			star.setTwitterUrl("https://twitter.com/CareyMOnline");
			star.setScreenName("@CareyMOnline");
			star.setTwitterName("CareyMulliganOnline");
			star.setProfileImgURL("http://upload.wikimedia.org/wikipedia/commons/8/8a/Carey_Mulligan_2010.jpg");
			star.setDescription("Carey Hannah Mulligan is an English actress. She made her film debut as Kitty Bennet in Pride & Prejudice in 2005. She has had roles in several British television programmes such as Doctor Who, Bleak House, and Northanger Abbey.");
		}

		if (name.equals("Christian Bale")) {
			star.setName("Christian Bale");
			star.setTwitterUrl("https://twitter.com/TheOfficialBale");
			star.setTwitterName("Christian Bale");
			star.setScreenName("@BradPittsPage");
			star.setProfileImgURL("http://www.eonline.com/eol_images/Entire_Site/20080723/300.bale.christian.072308.jpg");
			star.setDescription("Christian Charles Philip Bale is an English actor. He has starred in both blockbuster films and smaller projects from independent producers and art houses.");
		}

		if (name.equals("Brad Pitt")) {
			star.setName("Brad Pitt");
			star.setTwitterUrl("https://twitter.com/BradPittsPage");
			star.setTwitterName("Brad Pitt");
			star.setScreenName("@BradPittsPage");
			star.setProfileImgURL("http://aimworkout.com/wp-content/uploads/2014/11/Brad-Pitt.jpg");
			star.setDescription("William Bradley \"Brad\" Pitt is an American actor and producer. He has received a Golden Globe Award, a Screen Actors Guild Award, and three Academy Award nominations in acting categories, and received two further Academy Award nominations, winning one, for productions of his film production company Plan B Entertainment. He has been described as one of the world's most attractive men, a label for which he has received substantial media attention.");
		}

		if (name.equals("Tom Hardy")) {
			star.setName("Tom Hardy");
			star.setTwitterUrl("https://twitter.com/TomHardyOnline");
			star.setTwitterName("Tom Hardy Online");
			star.setScreenName("@TomHardyOnline");
			star.setProfileImgURL("https://pbs.twimg.com/profile_images/378800000224930600/0b4b1d42f5f686ef7269d4977f38721e_400x400.jpeg");
			star.setDescription("Edward Thomas \"Tom\" Hardy is an English actor. Notable film roles include the science fiction film Star Trek: Nemesis, the Guy Ritchie crime film, RocknRolla, the biographical psychological drama Bronson, the science fiction thriller Inception (2010), the sports drama Warrior (2011), the Cold War espionage film Tinker Tailor Soldier Spy (2011), the villain Bane in the superhero film The Dark Knight Rises (2012).");
		}

		if (name.equals("Ryan Gosling")) {
			star.setName("Ryan Gosling");
			star.setTwitterUrl("https://twitter.com/RyanGosling");
			star.setTwitterName("Ryan Gosling");
			star.setScreenName("@RyanGosling");
			star.setProfileImgURL("http://media1.s-nbcnews.com/j/streams%5C2012/April/120404%5C288504-120215-ryan-gosling.blocks_desktop_medium.jpg");
			star.setDescription("Ryan Thomas Gosling is a Canadian actor, film director, screenwriter, musician and businessman. He began his career as a child star on the Disney Channel's Mickey Mouse Club and went on to appear in other family entertainment programs including Are You Afraid of the Dark? (1995) and Goosebumps (1996).");
		}

		if (name.equals("Anthony Hopkins")) {
			star.setName("Anthony Hopkins");
			star.setTwitterUrl("https://twitter.com/Tony_Hopkins");
			star.setTwitterName("Anthony Hopkins");
			star.setScreenName("@Tony_Hopkins");
			star.setProfileImgURL("http://content6.flixster.com/rtactor/40/38/40384_pro.jpg");
			star.setDescription("Sir Philip Anthony Hopkins, CBE (born 31 December 1937) is a Welsh actor of film, stage, and television, and a composer and painter. After graduating from the Royal Welsh College of Music & Drama in 1957, he trained at the Royal Academy of Dramatic Art in London, and was then spotted by Laurence Olivier who invited him to join the Royal National Theatre. In 1968, he got his break in film in The Lion in Winter, playing Richard I.");
		}

		if (name.equals("Jared Leto")) {
			star.setName("Jared Leto");
			star.setTwitterUrl("https://twitter.com/JaredLeto");
			star.setTwitterName("Jared Leto");
			star.setScreenName("@JaredLeto");
			star.setProfileImgURL("http://a3.files.biography.com/image/upload/c_fill,dpr_1.0,g_face,h_300,q_80,w_300/MTE5NDg0MDU1NDMxMzE3MDA3.jpg");
			star.setDescription("Jared Leto (/lɛtoʊ/; born December 26, 1971) is an American actor, singer, songwriter, and director. After starting his career with television appearances in the early 1990s, Leto achieved recognition for his role as Jordan Catalano on the television series My So-Called Life (1994). He made his film debut in How to Make an American Quilt (1995) and received first notable critical praise for his performance in Prefontaine (1997). Leto played supporting roles in The Thin Red Line (1998), Fight Club (1998) and American Psycho (2000), as well as the lead role in Urban Legend (1998).");
		}

		if (name.equals("Jack Nicholson")) {
			star.setName("Jack Nicholson");
			star.setTwitterUrl("https://twitter.com/jackulator");
			star.setTwitterName("Jack Nicholson");
			star.setScreenName("@jackulator");
			star.setProfileImgURL("https://lh4.googleusercontent.com/-Mt7vhIGxcGk/AAAAAAAAAAI/AAAAAAAAABk/YYK78BfwpJM/photo.jpg");
			star.setDescription("John Joseph \"Jack\" Nicholson (born April 22, 1937) is an American actor, film director, producer, and writer. Throughout his career, Nicholson has portrayed unique and challenging roles, many of which include dark portrayals of excitable, neurotic and psychopathic characters. Nicholson's 12 Academy Award nominations make him the most nominated male actor in the Academy's history.");
		}

		if (name.equals("Benedict Cumberbatch")) {
			star.setName("Benedict Cumberbatch");
			star.setTwitterUrl("https://twitter.com/Cumberbitches");
			star.setTwitterName("Benedict Cumberbatch");
			star.setScreenName("@Cumberbitches");
			star.setProfileImgURL("https://lh4.googleusercontent.com/-lvuY--my18M/AAAAAAAAAAI/AAAAAAAAABw/FRAy1dcVbw0/photo.jpg");
			star.setDescription("Benedict Timothy Carlton Cumberbatch (born 19 July 1976) is an English actor and film producer who has performed in film, television, theatre and radio. The son of actors Timothy Carlton and Wanda Ventham, he graduated from the University of Manchester and continued his training at the London Academy of Music and Dramatic Art, obtaining an MA in Classical Acting. He first performed at the Open Air Theatre, Regent's Parkin Shakespearean productions such as Love's Labour's Lost (2001), A Midsummer Night's Dream (2001), and Romeo and Juliet (2002).");
		}

		if (name.equals("Jim Sturgess")) {
			star.setName("Jim Sturgess");
			star.setTwitterUrl("https://twitter.com/mrjimsturgess");
			star.setTwitterName("Jim Sturgess");
			star.setScreenName("@mrjimsturgess");
			star.setProfileImgURL("http://upload.wikimedia.org/wikipedia/commons/b/b5/Jim_Sturgess.jpg");
			star.setDescription("James Anthony \"Jim\" Sturgess (born 16 May 1981) is an English actor and singer-songwriter. His breakthrough role was appearing as Jude in the musical romance drama film Across the Universe (2007). In 2008, he played the male lead role of Ben Campbell in 21, a film about five MIT students, who, by counting cards, take Las Vegas casinos for millions. Sturgess's co-stars in 21 include Kevin Spacey and Laurence Fishburne.");
		}

		if (name.equals("Dakota Fanning")) {
			star.setName("Dakota Fanning");
			star.setTwitterUrl("https://twitter.com/itsDakotaFannin");
			star.setTwitterName("Dakota Fanning");
			star.setScreenName("@itsDakotaFannin");
			star.setProfileImgURL("http://a3.files.biography.com/image/upload/c_fill,dpr_1.0,g_face,h_300,q_80,w_300/MTIwNjA4NjMzNzAxNDM0ODky.jpg");
			star.setDescription("Hannah Dakota Fanning (born February 23, 1994), is an American actress who rose to prominence after her breakthrough performance at age seven in the 2001 film I Am Sam. Her performance earned her a nomination for a Screen Actors Guild Award at age eight in 2002, making her the youngest nominee in history. As a child actress, she went on to appear in high-profile films such as Man on Fire (2004), War of the Worlds (2005) and Charlotte's Web (2006).");
		}

		if (name.equals("Amy Adams")) {
			star.setName("Amy Adams");
			star.setTwitterUrl("https://twitter.com/amyonlineorg");
			star.setTwitterName("Amy Adams");
			star.setScreenName("@amyonlineorg");
			star.setProfileImgURL("http://a4.files.biography.com/image/upload/c_fill,dpr_1.0,g_face,h_300,q_80,w_300/MTIwNjA4NjM0MTU1NDAyNzY0.jpg");
			star.setDescription("Amy Lou Adams (born August 20, 1974) is an American actress and singer. Adams began her career on stage performing in dinner theatre and later made her feature film debut in Drop Dead Gorgeous. After moving to Los Angeles and appearing in a series of television guest appearances and roles in B movies, Adams appeared as Brenda Strong in Steven Spielberg's Frank Abagnale biopic Catch Me If You Can. Her breakthrough role came in the 2005 independent film Junebug, for which she received critical acclaim and her first of four Academy Award nominations for Best Supporting Actress.");
		}

		if (name.equals("Anne Hathaway")) {
			star.setName("Anne Hathaway");
			star.setTwitterUrl("https://twitter.com/Annehway");
			star.setTwitterName("Anne Hathaway");
			star.setScreenName("@Annehway");
			star.setProfileImgURL("http://media1.onsugar.com/files/2013/01/02/0/192/1922153/2de36737ffa44eca_Anne-Hathaway.xxxlarge_1.jpg");
			star.setDescription("Anne Jacqueline Hathaway (born November 12, 1982) is an American actress, producer, and singer. After several stage roles, she appeared in the 1999 television series Get Real. She came to prominence after playing Mia Thermopolis in the Disney film The Princess Diaries (2001) and in its 2004 sequel. Since then, Hathaway has starred in dramatic films such as Havoc and Brokeback Mountain, in 2005.");
		}

		if (name.equals("Krysten Ritter")) {
			star.setName("Krysten Ritter");
			star.setTwitterUrl("https://twitter.com/Krystenritter");
			star.setTwitterName("krysten ritter");
			star.setScreenName("@Krystenritter");
			star.setProfileImgURL("http://cdn3.belfasttelegraph.co.uk/entertainment/film-tv/news/article29409218.ece/68ace/ALTERNATES/h342/PANews%20BT_N0092261373449209937A_I1.jpg");
			star.setDescription("Krysten Alyce Ritter (born December 16, 1981) is an American actress, musician, and former model. Ritter is known for her roles as Jane Margolis on the AMC drama series Breaking Bad and Chloe on the ABCcomedy series Don't Trust the B---- in Apartment 23. She has appeared in films such as What Happens in Vegas (2008), 27 Dresses (2008), Confessions of a Shopaholic (2009), She's Out of My League (2010), and Big Eyes (2014).");
		}

		if (name.equals("Natalie Portman")) {
			star.setName("Natalie Portman");
			star.setTwitterUrl("https://twitter.com/natpdotcom");
			star.setTwitterName("NataliePortman.com");
			star.setScreenName("@natpdotcom");
			star.setProfileImgURL("http://a2.files.biography.com/image/upload/c_fill,dpr_1.0,g_face,h_300,q_80,w_300/MTE4MDAzNDEwNzU3MDYwMTEw.jpg");
			star.setDescription("Natalie Portman (born Neta-Lee Hershlag, June 9, 1981) is an Israeli-born American (with dual citizenship) actress, producer, and director. Her first role was in the 1994 action thrillerLéon: The Professional, opposite Jean Reno, but mainstream success came when she was cast as Padmé Amidala in the Star Wars prequel trilogy (released in 1999, 2002 and 2005).");
		}

		if (name.equals("Jennifer Lawrence")) {
			star.setName("Jennifer Lawrence");
			star.setTwitterUrl("https://twitter.com/JLdaily");
			star.setTwitterName("Jennifer Lawrence");
			star.setScreenName("@JLdaily");
			star.setProfileImgURL("http://s.plurielles.fr/mmdia/i/02/8/jennifer-lawrence-lors-de-la-conference-de-presse-de-hunger-games-11031028lwkvs_2041.jpg?v=1");
			star.setDescription("Jennifer Shrader Lawrence (born August 15, 1990) is an American actress. Her first major role was as a lead cast member on the TBS sitcom The Bill Engvall Show (2007–09). She appeared in the independent dramasThe Burning Plain (2008) and Winter's Bone (2010), for which she received an Academy Award for Best Actress nomination.");
		}

		if (name.equals("Emma Watson")) {
			star.setName("Emma Watson");
			star.setTwitterUrl("https://twitter.com/EmWatson");
			star.setTwitterName("Emma Watson");
			star.setScreenName("@EmWatson");
			star.setProfileImgURL("http://yinyang.urgente24.com/sites/default/files/emma_watson_7.jpg");
			star.setDescription("Emma Charlotte Duerre Watson (born 15 April 1990) is an English actress, model, and activist. Watson rose to prominence as Hermione Granger in the Harry Potter film series, appearing in all eight Harry Potter films from 2001 to 2011, previously having acted only in school plays. The franchise earned Watson worldwide fame, critical accolades, and more than £10 million.");
		}

		// if (name.equals()) {
		// star.setName();
		// star.setTwitterUrl();
		// star.setTwitterName();
		// star.setScreenName();
		// star.setProfileImgURL();
		// star.setDescription();
		// }

	}

}
