package forms;

import base.baseForm.BaseForm;
import base.elements.Button;
import base.elements.TextField;
import org.openqa.selenium.By;

import java.util.List;

public class UserPageForm extends BaseForm {

    private TextField userIdPost = null;
    private TextField userTextPost = null;
    private TextField userImgPost = null;
    private TextField userCommentOnPostId = null;
    private TextField userCommentOnPostMessage = null;
    private Button userPostLike = null;
    private String pathToCorrectPost;
    private String userId;
    private String postId;

    public UserPageForm(String userId, String postId) {
        super(new TextField(By.xpath("//div[@class='BasisProfile pcont fit_box']"), "Users  page"));
        pathToCorrectPost = "//div[@id = 'wall" + userId + "_" + postId + "']";
        this.userId = userId;
        this.postId = postId;
    }

    public String getTextFromNewPost(String text) {
        userTextPost = getIFactory().getTextField(By.xpath(pathToCorrectPost + "//div[@class='pi_text zoom_text']"), "Element from new post, by text message");
        return userTextPost.getText();
    }

    public String getPhotoIdFromEditPost() {
        userImgPost = getIFactory().getTextField(By.xpath(pathToCorrectPost + "//div[@class='thumb_map_img thumb_map_img_as_div']"), "Img in post");
        return userImgPost.getTextAttribute("data-id");
    }

    public String getIdUserInComment() {
        getIFactory().getButton(By.xpath("//a[@class='PostBottomButton _item_replies PostBottomButton--non-shrinkable']//span[@class='PostBottomButton__icon']"), "Comment stub").jsMethods().clickOnElement();
        userCommentOnPostId = getIFactory().getTextField(By.id("wall"+userId+"_"+postId+"_replies"), "Comment in correct post");
        return userCommentOnPostId.getTextAttribute("id");
    }

    public String getCommentMessage() {
        userCommentOnPostMessage = getIFactory().getTextField(By.xpath("//div[@id='wall"+userId+"_"+postId+"_replies']//div[@class = 'ReplyItem__body']"), "Comment message");
        return userCommentOnPostMessage.getText();
    }

    public void addLikePost() {
        userPostLike = getIFactory().getButton(By.xpath(pathToCorrectPost + "//a[@class='PostBottomButton PostBottomButtonReaction']//span[@class='PostBottomButton__icon']"), "Like button");
        userPostLike.jsMethods().clickOnElement();
    }
}