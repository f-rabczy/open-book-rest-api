package pl.rabczynski.openbook.auth.util;

import org.springframework.security.core.context.SecurityContextHolder;
import pl.rabczynski.openbook.auth.domain.CustomUserDetails;

public class UserDetailsUtil {

    public static Integer getCurrentUserId() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getId();
    }
}
