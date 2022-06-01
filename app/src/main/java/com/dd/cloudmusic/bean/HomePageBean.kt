package com.dd.cloudmusic.bean

data class HomePageBean(
    val blockCodeOrderList: Any,
    val blockUUIDs: Any,
    val blocks: List<Block>,
    val cursor: Any,
    val guideToast: GuideToast,
    val hasMore: Boolean,
    val internalTest: Any,
    val pageConfig: PageConfig,
    val titles: List<Any>
)

data class Block(
    val action: String,
    val actionType: String,
    val blockCode: String,
    val blockStyle: Int,
    val canClose: Boolean,
    val creatives: List<Creative>,
    val crossPlatformConfig: CrossPlatformConfig,
    val defaultTab: String,
    val dislikeShowType: Int,
    val extInfo: Any,
    val showType: String,
    val uiElement: UiElementXX
)

data class GuideToast(
    val hasGuideToast: Boolean,
    val toastList: List<Any>
)

data class PageConfig(
    val abtest: List<String>,
    val fullscreen: Boolean,
    val homepageMode: String,
    val nodataToast: String,
    val orderInfo: String,
    val refreshInterval: Int,
    val refreshToast: String,
    val showModeEntry: Boolean,
    val songLabelMarkLimit: Int,
    val songLabelMarkPriority: List<String>,
    val title: Any
)

data class Creative(
    val action: String,
    val actionType: String,
    val alg: String,
    val code: String,
    val creativeId: String,
    val creativeType: String,
    val logInfo: String,
    val position: Int,
    val resources: List<Resource>,
    val uiElement: UiElementX
)

data class CrossPlatformConfig(
    val containerType: String,
    val rnContent: RnContent
)

data class UiElementXX(
    val button: ButtonX,
    val rcmdShowType: String,
    val subTitle: SubTitleXX
)

data class Resource(
    val action: String,
    val actionType: String,
    val alg: String,
    val logInfo: String,
    val resourceExtInfo: ResourceExtInfo,
    val resourceId: String,
    val resourceType: String,
    val resourceUrl: Any,
    val uiElement: UiElement,
    val valid: Boolean
)

data class UiElementX(
    val button: Button,
    val image: ImageX,
    val labelTexts: List<String>,
    val mainTitle: MainTitleX,
    val rcmdShowType: String,
    val subTitle: SubTitleX
)

data class ResourceExtInfo(
    val alias: String,
    val artists: List<Artist>,
    val bigEvent: Boolean,
    val canSubscribe: Boolean,
    val commentSimpleData: CommentSimpleData,
    val djProgram: DjProgram,
    val endTime: Long,
    val eventId: String,
    val eventType: String,
    val highQuality: Boolean,
    val playCount: Long,
    val song: Song,
    val songData: SongData,
    val songPrivilege: SongPrivilege,
    val specialCover: Int,
    val specialType: Int,
    val startTime: Long,
    val subed: Boolean,
    val tags: List<String>
)

data class UiElement(
    val image: Image,
    val labelTexts: List<String>,
    val labelType: String,
    val labelUrls: List<String>,
    val mainTitle: MainTitle,
    val rcmdShowType: String,
    val subTitle: SubTitle,
    val tagImage: TagImage
)

data class Artist(
    val albumSize: Int,
    val alias: List<Any>,
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

data class CommentSimpleData(
    val commentId: Long,
    val content: String,
    val threadId: String,
    val userId: Int,
    val userName: String
)

data class DjProgram(
    val adjustedPlayCount: Int,
    val auditStatus: Int,
    val bdAuditStatus: Int,
    val blurCoverUrl: Any,
    val buyed: Boolean,
    val canReward: Boolean,
    val category: String,
    val categoryId: Int,
    val channels: List<String>,
    val commentThreadId: String,
    val coverId: Long,
    val coverUrl: String,
    val createEventId: Int,
    val createTime: Long,
    val description: String,
    val disPlayStatus: String,
    val dj: Dj,
    val djPlayRecordVo: Any,
    val duration: Int,
    val extProperties: Any,
    val h5Links: List<Any>,
    val id: Long,
    val listenerCount: Int,
    val mainSong: MainSong,
    val mainTrackId: Int,
    val name: String,
    val privacy: Boolean,
    val programDesc: Any,
    val programFeeType: Int,
    val pubStatus: Int,
    val publish: Boolean,
    val radio: Radio,
    val reward: Boolean,
    val scheduledPublishTime: Long,
    val secondCategory: String,
    val secondCategoryId: Int,
    val serialNum: Int,
    val songs: Any,
    val subscribedCount: Int,
    val titbitImages: Any,
    val titbits: Any,
    val trackCount: Int,
    val updateTime: Long,
    val userId: Long,
    val xInfo: Any
)

data class Song(
    val a: Any,
    val al: Al,
    val alia: List<String>,
    val ar: List<Ar>,
    val cd: String,
    val cf: String,
    val copyright: Int,
    val cp: Int,
    val crbt: Any,
    val djId: Int,
    val dt: Int,
    val entertainmentTags: Any,
    val fee: Int,
    val ftype: Int,
    val h: H,
    val hr: Any,
    val id: Int,
    val l: L,
    val m: M,
    val mark: Long,
    val mst: Int,
    val mv: Int,
    val name: String,
    val no: Int,
    val noCopyrightRcmd: Any,
    val originCoverType: Int,
    val originSongSimpleData: Any,
    val pc: Pc,
    val pop: Int,
    val pst: Int,
    val publishTime: Long,
    val resourceState: Boolean,
    val rt: String,
    val rtUrl: Any,
    val rtUrls: List<Any>,
    val rtype: Int,
    val rurl: Any,
    val s_id: Int,
    val single: Int,
    val songJumpInfo: Any,
    val sq: Sq,
    val st: Int,
    val t: Int,
    val tagPicList: Any,
    val tns: List<String>,
    val v: Int,
    val version: Int,
    val videoInfo: VideoInfo
)

data class SongData(
    val album: AlbumX,
    val alias: List<String>,
    val artists: List<ArtistXXXXXX>,
    val audition: Any,
    val bMusic: BMusicX,
    val commentThreadId: String,
    val copyFrom: String,
    val copyright: Int,
    val copyrightId: Int,
    val crbt: Any,
    val dayPlays: Int,
    val disc: String,
    val duration: Int,
    val fee: Int,
    val ftype: Int,
    val hMusic: HMusicX,
    val hearTime: Int,
    val hrMusic: Any,
    val id: Int,
    val lMusic: LMusicX,
    val mMusic: MMusic,
    val mark: Int,
    val mp3Url: Any,
    val mvid: Int,
    val name: String,
    val no: Int,
    val noCopyrightRcmd: Any,
    val originCoverType: Int,
    val originSongSimpleData: Any,
    val playedNum: Int,
    val popularity: Int,
    val position: Int,
    val ringtone: String,
    val rtUrl: Any,
    val rtUrls: List<Any>,
    val rtype: Int,
    val rurl: Any,
    val score: Int,
    val sign: Any,
    val single: Int,
    val sqMusic: SqMusic,
    val starred: Boolean,
    val starredNum: Int,
    val status: Int,
    val transName: Any,
    val transNames: List<String>
)

data class SongPrivilege(
    val chargeInfoList: List<ChargeInfo>,
    val cp: Int,
    val cs: Boolean,
    val dl: Int,
    val dlLevel: String,
    val downloadMaxBrLevel: String,
    val downloadMaxbr: Int,
    val fee: Int,
    val fl: Int,
    val flLevel: String,
    val flag: Int,
    val freeTrialPrivilege: FreeTrialPrivilege,
    val id: Int,
    val maxBrLevel: String,
    val maxbr: Int,
    val paidBigBang: Boolean,
    val payed: Int,
    val pc: Any,
    val pl: Int,
    val plLevel: String,
    val playMaxBrLevel: String,
    val playMaxbr: Int,
    val preSell: Boolean,
    val realPayed: Int,
    val rscl: Any,
    val sp: Int,
    val st: Int,
    val subp: Int,
    val toast: Boolean
)

data class Dj(
    val accountStatus: Int,
    val anchor: Boolean,
    val authStatus: Int,
    val authenticationTypes: Int,
    val authority: Int,
    val avatarDetail: Any,
    val avatarImgId: Long,
    val avatarImgIdStr: String,
    val avatarUrl: String,
    val backgroundImgId: Long,
    val backgroundImgIdStr: String,
    val backgroundUrl: String,
    val birthday: Long,
    val city: Int,
    val defaultAvatar: Boolean,
    val description: String,
    val detailDescription: String,
    val djStatus: Int,
    val expertTags: Any,
    val experts: Any,
    val followed: Boolean,
    val gender: Int,
    val mutual: Boolean,
    val nickname: String,
    val province: Int,
    val remarkName: Any,
    val signature: String,
    val userId: Long,
    val userType: Int,
    val vipType: Int
)

data class MainSong(
    val album: Album,
    val alias: List<Any>,
    val artists: List<ArtistXXX>,
    val audition: Any,
    val bMusic: BMusic,
    val commentThreadId: String,
    val copyFrom: String,
    val copyright: Int,
    val copyrightId: Int,
    val crbt: Any,
    val dayPlays: Int,
    val disc: String,
    val duration: Int,
    val extProperties: Any,
    val fee: Int,
    val ftype: Int,
    val hMusic: HMusic,
    val hearTime: Int,
    val hrMusic: Any,
    val id: Int,
    val lMusic: LMusic,
    val mMusic: Any,
    val mark: Int,
    val mp3Url: Any,
    val mvid: Int,
    val name: String,
    val no: Int,
    val noCopyrightRcmd: Any,
    val originCoverType: Int,
    val originSongSimpleData: Any,
    val playedNum: Int,
    val popularity: Int,
    val position: Int,
    val ringtone: String,
    val rtUrl: Any,
    val rtUrls: List<Any>,
    val rtype: Int,
    val rurl: Any,
    val score: Int,
    val sign: Any,
    val single: Int,
    val sqMusic: Any,
    val starred: Boolean,
    val starredNum: Int,
    val status: Int,
    val transName: Any,
    val xInfo: Any
)

data class Radio(
    val buyed: Boolean,
    val category: String,
    val categoryId: Int,
    val composeVideo: Boolean,
    val createTime: Long,
    val desc: String,
    val descPicList: List<Any>,
    val discountPrice: Any,
    val dj: Any,
    val `dynamic`: Boolean,
    val extProperties: Any,
    val feeScope: Int,
    val finished: Boolean,
    val hightQuality: Boolean,
    val icon: Any,
    val id: Int,
    val intervenePicId: Long,
    val intervenePicUrl: String,
    val lastProgramCreateTime: Long,
    val lastProgramId: Long,
    val lastProgramName: Any,
    val liveInfo: Any,
    val manualTagsDTO: ManualTagsDTO,
    val name: String,
    val originalPrice: Int,
    val picId: Long,
    val picUrl: String,
    val playCount: Int,
    val price: Int,
    val privacy: Boolean,
    val programCount: Int,
    val purchaseCount: Int,
    val radioFeeType: Int,
    val rcmdText: String,
    val scoreInfoDTO: Any,
    val secondCategory: String,
    val secondCategoryId: Int,
    val shortName: Any,
    val subCount: Int,
    val taskId: Int,
    val underShelf: Boolean,
    val videos: Any,
    val whiteList: Boolean,
    val xInfo: Any
)

data class Album(
    val alias: List<Any>,
    val artist: ArtistX,
    val artists: List<ArtistXX>,
    val blurPicUrl: Any,
    val briefDesc: String,
    val commentThreadId: String,
    val company: Any,
    val companyId: Int,
    val copyrightId: Int,
    val description: String,
    val extProperties: Any,
    val gapless: Int,
    val id: Int,
    val mark: Int,
    val name: String,
    val onSale: Boolean,
    val pic: Long,
    val picId: Long,
    val picUrl: String,
    val publishTime: Int,
    val size: Int,
    val songs: List<Any>,
    val status: Int,
    val subType: Any,
    val tags: String,
    val transName: Any,
    val type: Any,
    val xInfo: Any
)

data class ArtistXXX(
    val albumSize: Int,
    val alias: List<Any>,
    val briefDesc: String,
    val extProperties: Any,
    val id: Int,
    val img1v1Id: Int,
    val img1v1Url: String,
    val musicSize: Int,
    val name: String,
    val picId: Int,
    val picUrl: String,
    val topicPerson: Int,
    val trans: String,
    val xInfo: Any
)

data class BMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extProperties: Any,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Float,
    val xInfo: Any
)

data class HMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extProperties: Any,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Float,
    val xInfo: Any
)

data class LMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extProperties: Any,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Float,
    val xInfo: Any
)

data class ArtistX(
    val albumSize: Int,
    val alias: List<Any>,
    val briefDesc: String,
    val extProperties: Any,
    val id: Int,
    val img1v1Id: Int,
    val img1v1Url: String,
    val musicSize: Int,
    val name: String,
    val picId: Int,
    val picUrl: String,
    val topicPerson: Int,
    val trans: String,
    val xInfo: Any
)

data class ArtistXX(
    val albumSize: Int,
    val alias: List<Any>,
    val briefDesc: String,
    val extProperties: Any,
    val id: Int,
    val img1v1Id: Int,
    val img1v1Url: String,
    val musicSize: Int,
    val name: String,
    val picId: Int,
    val picUrl: String,
    val topicPerson: Int,
    val trans: String,
    val xInfo: Any
)

data class ManualTagsDTO(
    val brandColumnTags: Any,
    val contentDescTags: ContentDescTags,
    val hotTags: Any,
    val themeDescTags: Any
)

data class ContentDescTags(
    val id: Int,
    val tagGroupName: Any,
    val tagList: List<Any>
)

data class Al(
    val id: Int,
    val name: String,
    val pic: Long,
    val picUrl: String,
    val pic_str: String,
    val tns: List<Any>
)

data class Ar(
    val alias: List<Any>,
    val id: Int,
    val name: String,
    val tns: List<Any>
)

data class H(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Float
)

data class L(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Float
)

data class M(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Float
)

data class Pc(
    val alb: String,
    val ar: String,
    val br: Int,
    val cid: String,
    val fn: String,
    val nickname: String,
    val sn: String,
    val uid: Int,
    val version: Int
)

data class Sq(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Float
)

data class VideoInfo(
    val moreThanOne: Boolean,
    val video: Any
)

data class AlbumX(
    val alias: List<String>,
    val artist: ArtistXXXX,
    val artists: List<ArtistXXXXX>,
    val blurPicUrl: String,
    val briefDesc: String,
    val commentThreadId: String,
    val company: String,
    val companyId: Int,
    val copyrightId: Int,
    val description: String,
    val gapless: Int,
    val id: Int,
    val mark: Int,
    val name: String,
    val onSale: Boolean,
    val pic: Long,
    val picId: Long,
    val picId_str: String,
    val picUrl: String,
    val publishTime: Long,
    val size: Int,
    val songs: List<Any>,
    val status: Int,
    val subType: String,
    val tags: String,
    val transName: Any,
    val type: String
)

data class ArtistXXXXXX(
    val albumSize: Int,
    val alias: List<Any>,
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

data class BMusicX(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Float
)

data class HMusicX(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Float
)

data class LMusicX(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Float
)

data class MMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Float
)

data class SqMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Float
)

data class ArtistXXXX(
    val albumSize: Int,
    val alias: List<Any>,
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

data class ArtistXXXXX(
    val albumSize: Int,
    val alias: List<Any>,
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

data class ChargeInfo(
    val chargeMessage: Any,
    val chargeType: Int,
    val chargeUrl: Any,
    val rate: Int
)

data class FreeTrialPrivilege(
    val listenType: Any,
    val resConsumable: Boolean,
    val userConsumable: Boolean
)

data class Image(
    val imageUrl: String
)

data class MainTitle(
    val title: String,
    val titleImgUrl: String
)

data class SubTitle(
    val title: String,
    val titleType: String
)

data class TagImage(
    val height: Int,
    val imageUrl: String,
    val width: Int
)

data class Button(
    val action: String,
    val actionType: String,
    val iconUrl: Any,
    val text: String
)

data class ImageX(
    val imageUrl: String
)

data class MainTitleX(
    val title: String
)

data class SubTitleX(
    val title: String
)

data class RnContent(
    val component: String,
    val engineId: String,
    val estimatedHeight: Int,
    val estimatedRatio: String,
    val moduleName: String,
    val params: Params
)

class Params

data class ButtonX(
    val action: String,
    val actionType: String,
    val iconUrl: Any,
    val text: String
)

data class SubTitleXX(
    val title: String,
    val titleImgUrl: String
)