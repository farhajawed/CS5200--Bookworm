package com.bookstoremanagement.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.bookstoremanagement.PO.RegistrationPO;

@Component("registrationValidator")
public class RegistrationValidation {
	public boolean supports(Class<?> klass) {
		return RegistrationPO.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors) {
		try {
			RegistrationPO registration = (RegistrationPO) target;
			System.out.println("errors.hasErrors() - "+errors.hasErrors());
			System.out.println("errors.getAllErrors() - "+errors.getAllErrors());
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
					"NotEmpty.registration.userLogin.userName",
					"User Name must not be Empty.");

			String userName = registration.getUsername();
			if (userName!= null && (userName.length()) > 20) {
				errors.rejectValue("userName",
						"lengthOfUser.registration.userLogin.userName",
						"User Name must not more than 20 characters.");
			}
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
					"NotEmpty.registration.userLogin.password",
					"Password must not be Empty.");
//			if (registration.getPassword() != null && registration.getPassword().length() < 4 || registration.getPassword().length() > 20) {
//				errors.rejectValue("password",
//						"lengthOfPassword.registration.userLogin.password",
//						"Password cannot be less than 4 characters or more than 20 characters.");
//			}
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname",
					"NotEmpty.registration.firstname",
					"Firstname must not be Empty.");
//			if (registration.getFirstname() != null && registration.getFirstname().length() < 4 || registration.getFirstname().length() > 20) {
//				errors.rejectValue("firstname",
//						"lengthOfFirstname.registration.firstname",
//						"Firstname cannot be less than 4 characters or more than 20 characters.");
//			}
//			if (registration.getLastname() != null &&  registration.getLastname().length() < 4 || registration.getLastname().length() > 20) {
//				errors.rejectValue("lastname",
//						"lengthOfLastname.registration.lastname",
//						"Lastname cannot be less than 4 characters or more than 20 characters.");
//			}
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
					"NotEmpty.registration.email",
					"Email must not be Empty.");

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob",
					"NotEmpty.registration.dob",
					"Date of Birth must not be Empty.");
			if (registration.getPhone() == 0) {
				errors.rejectValue("phone",
						"NotEmpty.registration.phone",
						"Phone number nust not be Empty");
			}

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street1",
					"NotEmpty.registration.street1",
					"Street1 must not be Empty.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city",
					"NotEmpty.registration.city",
					"City must not be Empty.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state",
					"NotEmpty.registration.state",
					"State must not be Empty.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pincode",
					"NotEmpty.registration.pincode",
					"Pincode must not be Empty.");

//			if (registration.getStreet1() != null &&  registration.getStreet1().length() < 4 || registration.getStreet1().length() > 20) {
//				errors.rejectValue("street1",
//						"lengthOfStreet1.registration.street1",
//						"Street1 cannot be less than 4 characters or more than 20 characters.");
//			}
//			if (registration.getCity() != null &&  registration.getCity().length() < 4 || registration.getCity().length() > 20) {
//				errors.rejectValue("city",
//						"lengthOfCity.registration.city",
//						"City name cannot be less than 4 characters or more than 20 characters.");
//			}
//			if (registration.getState() != null && registration.getState().length() < 2 || registration.getState().length() > 20) {
//				errors.rejectValue("state",
//						"lengthOfState.registration.state",
//						"State name cannot be less than 2 characters or more than 20 characters.");
//			}
//			if (registration.getPincode() != null &&  registration.getPincode().length() != 6) {
//				errors.rejectValue("pincode",
//						"lengthOfPincode.registration.pincode",
//						"Pincode cannot be less than or more than 6 characters.");
//			}
			System.out.println("errors.getAllErrors() - "+errors.getObjectName());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}