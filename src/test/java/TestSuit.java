import api.ApiApplication;
import base.Appium;
import forms.*;
import models.PostPhotoModel;
import models.PostSendModel;
import models.UploadUrlModel;
import models.postModel.PostMessageModel;
import models.requestPhotoModel.RequestPhotoModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.StringUtils;

import static api.ApiApplication.*;
import static api.ApiApplication.savePhotoInAlbum;
import static settings.Settings.*;

public class TestSuit extends BaseClass {

    @Test
    public void test() {
        AuthenticationForm authenticationForm = new AuthenticationForm();
        Assert.assertTrue(authenticationForm.isDisplayed(), "Page was not open");
        authenticationForm.clickToEntire();
        EntireForm entireForm = new EntireForm();
        entireForm.enterAndLogin(config.get("login").toString(), config.get("password").toString());
        NewsForm newsForm = new NewsForm();
        Assert.assertTrue(newsForm.isDisplayed(), "Page was not open");
        newsForm.goToMenu();
        MenuForm menuForm = new MenuForm();
        Assert.assertTrue(menuForm.isDisplayed(), "Page was not open");
        menuForm.toUserPage();
        String firstText = StringUtils.randomText();
        String userId = config.get("userId").toString();
        PostSendModel postSendModel = publishPost(config.get("userId").toString(), firstText);
        UserPageForm userPageForm = new UserPageForm(userId, String.valueOf(postSendModel.getResponse()));
        Appium.browser().refresh();
        Assert.assertEquals(userPageForm.getTextFromNewPost(firstText), firstText, "Users post do not contains correct message");
        UploadUrlModel uploadUrlModel = getUploadUrl(userId);
        PostPhotoModel postPhotoModel = getPhotoAttribute(uploadUrlModel.getUpload_url());
        RequestPhotoModel requestPhotoModel = savePhotoInAlbum(userId, postPhotoModel);
        String textSecond = StringUtils.randomText();
        ApiApplication.editPostMessage(userId, postSendModel.getResponse(), requestPhotoModel.getResponse().get(0).getId(), textSecond);
        Appium.browser().refresh();
        Assert.assertTrue(userPageForm.getPhotoIdFromEditPost().contains(String.valueOf(requestPhotoModel.getResponse().get(0).getId())));
        String textThird = StringUtils.randomText();
        ApiApplication.addCommentOnPost(userId, postSendModel.getResponse(), textThird);
        Assert.assertTrue(userPageForm.getIdUserInComment().contains(userId),"Comment was not create correct users");
        Assert.assertEquals(userPageForm.getCommentMessage(), textThird, "Comment message was not correct");
        userPageForm.addLikePost();
        PostMessageModel postMessageModel = ApiApplication.getPostById(userId, postSendModel.getResponse());
        Assert.assertTrue(!postMessageModel.getResponse().getItems().get(0).getLikes().getUserLikes().toString().isEmpty(),"Like is not users");
        ApiApplication.deletePostById(userId, postSendModel.getResponse());
    }
}