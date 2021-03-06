import kotlinx.serialization.Serializable

@Serializable
data class HomePageBean(
    val blocks: List<Block>,
)
@Serializable
data class Block(
    val action: String,
    val actionType: String,
    val blockCode: String,
    val blockStyle: Int,
    val canClose: Boolean,
    val canFeedback: Boolean,
    val creatives: List<Creative>,
    val dislikeShowType: Int,
    val resourceIdList: List<String>,
    val showType: String,
    val uiElement: UiElementXX
)
@Serializable
data class Creative(
    val action: String,
    val actionType: String,
    val alg: String,
    val creativeId: String,
    val creativeType: String,
    val logInfo: String,
    val position: Int,
    val resources: List<Resource>,
    val uiElement: UiElementX
)
@Serializable
data class UiElementXX(
    val button: Button,
    val rcmdShowType: String,
    val subTitle: SubTitleX
)
@Serializable
data class Resource(
    val action: String,
    val actionType: String,
    val alg: String,
    val logInfo: String,
    val resourceContentList: String ="",
    val resourceExtInfo: ResourceExtInfo,
    val resourceId: String,
    val resourceType: String,
    val resourceUrl: String ="",
    val uiElement: UiElement,
    val valid: Boolean
)
@Serializable
data class UiElementX(
    val image: ImageX,
    val labelTexts: List<String>,
    val mainTitle: MainTitleX,
    val rcmdShowType: String
)
@Serializable
data class ResourceExtInfo(
    val artists: List<Artist>,
    val highQuality: Boolean,
    val playCount: Int,
    val specialType: Int
)
@Serializable
data class UiElement(
    val image: Image,
    val labelTexts: List<String>,
    val mainTitle: MainTitle,
    val rcmdShowType: String,
    val subTitle: SubTitle
)
@Serializable
data class Artist(
    val albumSize: Int,
    val alias: List<String>,
    val briefDesc: String,
    val id: Int,
    val img1v1Id: Int,
    val img1v1Url: String,
    val musicSize: Int,
    val name: String,
    val picId: Int,
    val picUrl: String,
    val topicPerson: Int,
    val trans: String
)
@Serializable
data class Image(
    val imageUrl: String
)
@Serializable
data class MainTitle(
    val title: String
)
@Serializable
data class SubTitle(
    val title: String,
    val titleType: String
)
@Serializable
data class ImageX(
    val imageUrl: String
)
@Serializable
data class MainTitleX(
    val title: String
)
@Serializable
data class Button(
    val action: String,
    val actionType: String,
    val biData: String,
    val iconUrl: String,
    val text: String
)
@Serializable
data class SubTitleX(
    val title: String
)