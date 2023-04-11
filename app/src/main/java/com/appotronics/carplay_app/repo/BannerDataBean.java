package com.appotronics.carplay_app.repo;

import com.appotronics.carplay_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BannerDataBean {
    public Integer imageRes;
    public String imageUrl;
    public String title;
    public int viewType;

    public BannerDataBean(Integer imageRes, String title, int viewType) {
        this.imageRes = imageRes;
        this.title = title;
        this.viewType = viewType;
    }

    public BannerDataBean(String imageUrl, String title, int viewType) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.viewType = viewType;
    }

    public static List<BannerDataBean> getMainAtmosphereData() {
        List<BannerDataBean> list = new ArrayList<>();
        list.add(new BannerDataBean(R.mipmap.ic_atmosphere_1, "相信自己,你努力的样子真的很美", 1));
        list.add(new BannerDataBean(R.mipmap.ic_atmosphere_2, "极致简约,梦幻小屋", 3));
        list.add(new BannerDataBean(R.mipmap.ic_atmosphere_3, "超级卖梦人", 3));
        list.add(new BannerDataBean(R.mipmap.ic_atmosphere_4, "夏季新搭配", 1));
        list.add(new BannerDataBean(R.mipmap.ic_atmosphere_5, "绝美风格搭配", 1));
        return list;
    }

    public static List<BannerDataBean> getMainVideoData() {
        List<BannerDataBean> list = new ArrayList<>();
        list.add(new BannerDataBean(R.mipmap.ic_main_video_1, "沸雪首钢光影秀", 3));
        list.add(new BannerDataBean(R.mipmap.ic_main_video_2, "故宫上元之夜", 3));
        list.add(new BannerDataBean(R.mipmap.ic_main_video_3, "产品视频", 3));
        list.add(new BannerDataBean(R.mipmap.ic_main_video_4, "光峰品牌宣传片", 3));
        return list;
    }

    public static List<BannerDataBean> getVideoData() {
        List<BannerDataBean> list = new ArrayList<>();
        list.add(new BannerDataBean(R.mipmap.ic_video_banner_1, "相信自己,你努力的样子真的很美", 1));
        list.add(new BannerDataBean(R.mipmap.ic_video_banner_1, "极致简约,梦幻小屋", 3));
        list.add(new BannerDataBean(R.mipmap.ic_video_banner_1, "超级卖梦人", 3));
        list.add(new BannerDataBean(R.mipmap.ic_video_banner_1, "夏季新搭配", 1));
        list.add(new BannerDataBean(R.mipmap.ic_video_banner_1, "绝美风格搭配", 1));
        list.add(new BannerDataBean(R.mipmap.ic_video_banner_1, "微微一笑 很倾城", 3));
        return list;
    }

    /**
     * 仿淘宝商品详情第一个是视频
     * @return
     */
    public static List<BannerDataBean> getTestDataVideo() {
        List<BannerDataBean> list = new ArrayList<>();
        list.add(new BannerDataBean("http://vfx.mtime.cn/Video/2019/03/09/mp4/190309153658147087.mp4", "第一个放视频", 2));
        list.add(new BannerDataBean(R.mipmap.image7, "听风.赏雨", 1));
        list.add(new BannerDataBean(R.mipmap.image8, "迪丽热巴.迪力木拉提", 1));
        list.add(new BannerDataBean(R.mipmap.image9, "爱美.人间有之", 1));
        list.add(new BannerDataBean(R.mipmap.image10, "洋洋洋.气质篇", 1));
        list.add(new BannerDataBean(R.mipmap.image11, "生活的态度", 1));
        return list;
    }

    public static List<BannerDataBean> getTestData3() {
        List<BannerDataBean> list = new ArrayList<>();
        list.add(new BannerDataBean("https://img.zcool.cn/community/013de756fb63036ac7257948747896.jpg", null, 1));
        list.add(new BannerDataBean("https://img.zcool.cn/community/01639a56fb62ff6ac725794891960d.jpg", null, 1));
        list.add(new BannerDataBean("https://img.zcool.cn/community/01270156fb62fd6ac72579485aa893.jpg", null, 1));
        list.add(new BannerDataBean("https://img.zcool.cn/community/01233056fb62fe32f875a9447400e1.jpg", null, 1));
        list.add(new BannerDataBean("https://img.zcool.cn/community/016a2256fb63006ac7257948f83349.jpg", null, 1));
        return list;
    }

    public static List<BannerDataBean> getVideos() {
        List<BannerDataBean> list = new ArrayList<>();
        list.add(new BannerDataBean("http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4", null, 0));
        list.add(new BannerDataBean("http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4", null, 0));
        list.add(new BannerDataBean("http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4", null, 0));
        list.add(new BannerDataBean("http://vfx.mtime.cn/Video/2019/03/19/mp4/190319125415785691.mp4", null, 0));
        list.add(new BannerDataBean("http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4", null, 0));
        list.add(new BannerDataBean("http://vfx.mtime.cn/Video/2019/03/14/mp4/190314102306987969.mp4", null, 0));
        return list;
    }


    public static List<String> getColors(int size) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            list.add(getRandColor());
        }
        return list;
    }

    /**
     * 获取十六进制的颜色代码.例如  "#5A6677"
     * 分别取R、G、B的随机值，然后加起来即可
     *
     * @return String
     */
    public static String getRandColor() {
        String R, G, B;
        Random random = new Random();
        R = Integer.toHexString(random.nextInt(256)).toUpperCase();
        G = Integer.toHexString(random.nextInt(256)).toUpperCase();
        B = Integer.toHexString(random.nextInt(256)).toUpperCase();

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        return "#" + R + G + B;
    }
}
