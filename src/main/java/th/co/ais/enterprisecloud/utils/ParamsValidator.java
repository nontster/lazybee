package th.co.ais.enterprisecloud.utils;

import th.co.ais.enterprisecloud.exception.InvalidParameterException;
import th.co.ais.enterprisecloud.exception.MissingParameterException;

public interface ParamsValidator {
	public Boolean validate(th.co.ais.enterprisecloud.model.request.OrganizationType org) throws MissingParameterException, InvalidParameterException;
}
