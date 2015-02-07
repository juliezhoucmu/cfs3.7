package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateCustomerAccountForm extends FormBean {
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private String addr_line1;
	private String addr_line2;
	private String state;
	private String zip;
	private String city;

	public String getUsername() {
		return username;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPassword() {
		return password;
	}

	public String getAddr_line1() {
		return addr_line1;
	}

	public String getAddr_line2() {
		return addr_line2;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public String getCity() {
		return city;
	}

	public void setUsername(String s) {
		username = s.trim();
	}

	public void setPassword(String s) {
		password = s.trim();
	}

	public void setFirstname(String s) {
		firstname = s.trim();
	}

	public void setLastname(String s) {
		lastname = s.trim();
	}

	public void setAddr_line1(String s) {
		addr_line1 = s.trim();
	}

	public void setAddr_line2(String s) {
		addr_line2 = s.trim();
	}

	public void setState(String s) {
		state = s.trim();
	}

	public void setCity(String s) {
		city = s.trim();
	}

	public void setZip(String s) {
		zip = s.trim();
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (firstname == null || firstname.length() == 0)
			errors.add("customer firstname is required");
		if (lastname == null || lastname.length() == 0)
			errors.add("customer lastname is required");
		if (username == null || username.length() == 0)
			errors.add("customer username is required");
		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		} else if (password.length() < 6 || password.length() > 15) {
			errors.add("The length of password should be between 6 and 15");
		}

		if (firstname.matches(".*[<>?*\"';].*")
				|| lastname.matches(".*[<>?*\"';].*")
				|| username.matches(".*[<>?*\"';].*")
				|| password.matches(".*[<>?*\"';].*"))
			errors.add("username / firstname / lastname  may not contain angle brackets or quotes or semicolon");

		if (username.substring(0, 1).matches("[0-9]")) {
			errors.add("The first character of the username can't be a number");
		}

		if (addr_line1.contains("!") || addr_line1.contains("?")
				|| addr_line1.contains("*") || addr_line1.contains("&lt;")
				|| addr_line1.contains("&gt;") || addr_line1.contains("&amp;")
				|| addr_line1.contains("\"") || addr_line1.contains(";")
				|| addr_line1.contains("<") || addr_line1.contains(">")
				|| addr_line1.contains("'")) {
			errors.add("addr_line1 cannot contain <> ? ! * ; ' or quotes");
		}

		if (addr_line2.contains("!") || addr_line2.contains("?")
				|| addr_line2.contains("*") || addr_line2.contains("&lt;")
				|| addr_line2.contains("&gt;") || addr_line2.contains("&amp;")
				|| addr_line2.contains("\"") || addr_line1.contains("\"")
				|| addr_line1.contains(";") || addr_line1.contains("<")
				|| addr_line1.contains(">") || addr_line1.contains("'")) {

			errors.add("addr_line2 cannot contain <> ? ! * ; ' or quotes");
		}

		if (username.length() > 20) {
			errors.add("The length of username can't be more than 20");
		}
		if (firstname.length() > 20) {
			errors.add("The length of first name can't be more than 20");
		}
		if (lastname.length() > 20) {
			errors.add("The length of last name can't be more than 20");
		}

		if (addr_line1.length() > 50) {
			errors.add("The length of address line 1 cannot be more than 100");
		}
		if (addr_line2.length() > 100) {
			errors.add("The length of address line 2 cannot be more than 100");
		}
		if (city.length() > 20) {
			errors.add("The length of city cannot be more than 20");
		} else {
			for (int i = 0; i < city.length(); i++) {
				if (!Character.isLetter(city.charAt(i))) {
					errors.add("The name of the city is not made up by letters");
					break;
				}
			}
		}

		if (state.length() > 20) {
			errors.add("The length of state cannot be more than 20");
		} else {
			for (int i = 0; i < state.length(); i++) {
				if (!Character.isLetter(state.charAt(i))) {
					errors.add("The name of the state is not made up by letters");
					break;
				}
			}
		}

		if (zip.length() != 5) {
			errors.add("please input 5 digit zip code");
		} else {
			for (int i = 0; i < zip.length(); i++) {
				if (!Character.isDigit(zip.charAt(i))) {
					errors.add("zip code should not contain non-digits");
					break;
				}
			}
		}

		if (errors.size() > 0)
			return errors;
		return errors;

	}
}
