/**
 * 
 */
package de.evoila.cf.broker.exception;

/**
 * @author Christian Brinker, evoila.
 *
 */
public class ServiceInstanceBindingDoesNotExistsException extends Exception {
	private static final long serialVersionUID = -1879753092397657116L;

	private String bindingId;

	public ServiceInstanceBindingDoesNotExistsException(String bindingId) {
		this.bindingId = bindingId;
	}

	@Override
	public String getMessage() {
		return "ServiceInstanceBinding does not exist: id = " + bindingId;
	}
}

