package com.my.springbootisfun.user.service;

import com.my.springbootisfun.user.model.User;
import org.springframework.context.annotation.Scope;

public interface UserService {

    /**
     * Spring Bean Scope
     * =================
     * We can customize the scope of our bean my annotating the @Scope
     * @Scope("singleton") - Single bean entire IOC Container(Immutable)
     * @Scope("prototype") - Many bean to be exist or created
     * @Scope("request") - Many bean to be exist or created (for each HTTP Request)
     * @Scope("session") - Many bean to be exist or created (for each HTTP Session)
     * @Scope("application") - Single bean entire application
     * @Scope("websocket") - Web socket bean
     *
     * Let said we annotate below bean with singleton
     * - This can be demonstrate thru the usecase of multiple request to getUserInfo()
     * - The first request has using the getUserInfo bean
     * - Other requests need to wait the first request to be completed before using that bean
     */
    @Scope("singleton")
    public User getUserInfo();

    public User updateUserInfo(User payload);
}
