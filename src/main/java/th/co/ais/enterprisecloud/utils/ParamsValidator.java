package th.co.ais.enterprisecloud.utils;

import th.co.ais.enterprisecloud.exception.InvalidParameterException;
import th.co.ais.enterprisecloud.exception.MissingInputParameterException;

public interface ParamsValidator {
	public Boolean validate(th.co.ais.enterprisecloud.domain.request.OrganizationType org) throws MissingInputParameterException, InvalidParameterException;
}
