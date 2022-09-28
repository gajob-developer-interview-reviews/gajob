package go.nkrcd.web.oauth2.model;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public enum OAuthAttributes {
    GOOGLE("google", (attributes) -> {
        return new UserProfile(
                String.valueOf(attributes.get("sub")),
                (String) attributes.get("name"),
                (String) attributes.get("email"),
                (String) attributes.get("picture")
        );
    });

    private final String registrationId;
    private final Function<Map<String, Object>, UserProfile> of;    // 람다식을 이용하기 위한 FunctionalInterface

    OAuthAttributes(String registrationId, Function<Map<String, Object>, UserProfile> of) {
        this.registrationId = registrationId;
        this.of = of;
    }

    public static UserProfile extract(String registrationId, Map<String, Object> attributes) {
        return Arrays.stream(values())  // enum 의 요소들을 순서대로 배열에 리턴하여 이를 stream 으로 만들어줌
                .filter(provider -> registrationId.equals(provider.registrationId)) // filter : 요소들을 조건에 따라 걸러내는 작업 수행, provicer 가 일치하는 경우
                .findFirst()    // 일치하는 것 하나를 찾아줌
                .orElseThrow(IllegalArgumentException::new) // 일치하는 것이 없는 경우 IllegalArgumentException 발생
                .of.apply(attributes);  // 일치하는 것이 존재하면 UserProfile을 만들어서 반환
    }
}