package com.dotto.app.oauth.service.socical;

public interface OAuth {
    String getOauthURL();
    String getTokenURL(String code);
}
