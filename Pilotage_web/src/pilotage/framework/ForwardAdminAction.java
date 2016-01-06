package pilotage.framework;

public class ForwardAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = 1L;

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		return OK;
	}
}
