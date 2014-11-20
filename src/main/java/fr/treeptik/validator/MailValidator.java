package fr.treeptik.validator;

import java.util.Map;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.UserService;

@ManagedBean
public class MailValidator implements Validator {

	@EJB
	private UserService serviceUser;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public boolean emailValidator(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		return pattern.matcher(email.toString()).matches();

	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String email = String.valueOf(value);
		Map<String, Object> map = component.getAttributes();
		Integer idUser = (Integer) map.get("idUser");
		System.out.println("***idUser: " + idUser);
		System.out.println("***mail: " + email + " valid :"
				+ emailValidator(email) + "*");

		boolean valid = emailValidator(email);

		if (!valid) {

			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setDetail("#{msg['saisie.email.error_detail']}");
			message.setSummary("#{msg['saisie.email.error_summary']}");

			throw new ValidatorException(message);
		} else {
			try {
				if (serviceUser.exist(email, idUser) > 0) {
					FacesMessage message = new FacesMessage();
					message.setSummary("#{msg['saisie.email.deja.pris']}");
					throw new ValidatorException(message);
				}
			} catch (ServiceException e) {
				System.out.println("serviceUser.exist ");
			}
		}
	}
}