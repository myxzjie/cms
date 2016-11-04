package com.xzjie.gypt.system.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xzjie.gypt.common.utils.DateUtils;
import com.xzjie.gypt.common.utils.ImageBase64Utils;
import com.xzjie.gypt.common.utils.RspCode;
import com.xzjie.gypt.common.utils.result.MapResult;
import com.xzjie.gypt.system.model.Upload;
import com.xzjie.gypt.system.service.UploadService;
import com.xzjie.gypt.system.web.WebUtils;

import sun.misc.BASE64Decoder;


@SuppressWarnings("restriction")
@Controller
@RequestMapping("upload")
public class UploadFileController {

	@Autowired
	private UploadService uploadService;

	@RequestMapping(value = "base64/image")
	@ResponseBody
	public synchronized Map<String, Object> uploadImagebase64(String dir, String base64, String type, Upload entity) {

		String protocol = "data:" + type + ";base64,";
		int length = base64.indexOf(protocol) + protocol.length();

		base64 = base64.substring(length);

		String suffix = "." + type.substring(type.lastIndexOf("/") + 1);

		if (!isValid(suffix, WebUtils.getUploadImgageExts().split(","))) {// 检测图片类型
			return MapResult.mapError("上传失败，图片类型为:" + WebUtils.getUploadImgageExts());
		}

		if (entity.getFileSize() > WebUtils.getUploadImgageMaxSize()) {
			return MapResult.mapError("上传失败：文件大小不能超过[" + WebUtils.getUploadImgageMaxSize() / (1024 * 1024) + "M]");
		}

		String fileName = System.currentTimeMillis() + "_" + DateUtils.getRandom(6) + suffix;

		String path = WebUtils.getUploadImageDirectory() + dir;

		String filePath = path + fileName;

		setMkdir(path);

		try {
			ImageBase64Utils.base64ToImageFile(base64, filePath);

			String weburl = WebUtils.getUploadImageWeb() + dir + fileName;

			entity.setFileName(fileName);
			entity.setWebUrl(weburl);
			entity.setUriPath(filePath);
			// 类型，0图片，1视频，2音频
			entity.setStype(0);

			uploadService.save(entity);

			return MapResult.mapOK(entity, "OK");
		} catch (IOException e) {
			e.printStackTrace();
			return MapResult.mapOK(e.getMessage(), "OK");
		}

		// return MapResult.mapError("请选择上次的图片");
	}

	@RequestMapping(value = "image")
	@ResponseBody
	public synchronized Map<String, Object> uploadImage(String dir, @RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request) {

		/** 判断文件是否为空,空直接返回上传错误 **/
		if (!multipartFile.isEmpty()) {
			try {
				// 获得文件后缀名
				String suffix = multipartFile.getOriginalFilename()
						.substring(multipartFile.getOriginalFilename().lastIndexOf("."));
				if (!isValid(suffix, WebUtils.getUploadImgageExts().split(","))) {// 检测图片类型
					return MapResult.mapError("上传失败，图片类型为:" + WebUtils.getUploadImgageExts());
				}
				Long fileSize = multipartFile.getSize();
				if (fileSize > WebUtils.getUploadImgageMaxSize()) {
					return MapResult
							.mapError("上传失败：文件大小不能超过[" + WebUtils.getUploadImgageMaxSize() / (1024 * 1024) + "M]");
				}

				if (dir == null) {
					return MapResult.mapError("上传失败：dir 参数为空");
				}

				if (!dir.endsWith("/")) {
					dir = dir + "/";
				}

				String uploadFileName = multipartFile.getOriginalFilename();
				String fileName = System.currentTimeMillis() + "_" + DateUtils.getRandom(6) + suffix;
				;

				String path = WebUtils.getUploadImageDirectory() + dir;
				String filePath = path + fileName;

				setMkdir(path);

				multipartFile.transferTo(new File(filePath));

				// String weburl = uploadImageWeb + dir + fileName;
				String weburl = dir + fileName;

				Upload entity = new Upload();
				entity.setFileName(fileName);
				entity.setUploadFileName(uploadFileName);
				entity.setWebUrl(weburl);
				entity.setUriPath(filePath);
				// 类型，0图片，1视频，2音频
				entity.setStype(0);
				entity.setFileSize(Double.valueOf(fileSize));

				uploadService.save(entity);

				return MapResult.mapOK(entity, "OK");
			} catch (Exception e) {
				e.printStackTrace();
				return MapResult.mapOK(e.getMessage(), "OK");
			}
		}

		return MapResult.mapError("请选择上次的图片");
	}

	@RequestMapping("file")
	@ResponseBody
	public Map<String, Object> uploadFiles(String dir, @RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request) {
		/** 判断文件是否为空,空直接返回上传错误 **/
		if (!multipartFile.isEmpty()) {
			try {
				// 获得文件后缀名
				String suffix = multipartFile.getOriginalFilename()
						.substring(multipartFile.getOriginalFilename().lastIndexOf("."));
				if (!isValid(suffix, WebUtils.getUploadFileExts().split(","))) {// 检测图片类型
					return MapResult.mapError(RspCode.R99999, "上传失败，文件类型为:" + WebUtils.getUploadFileExts());
				}
				Long fileSize = multipartFile.getSize();
				if (fileSize > WebUtils.getUploadFileMaxSize()) {
					return MapResult.mapError(RspCode.R99999,
							"上传失败：文件大小不能超过[" + WebUtils.getUploadFileMaxSize() / (1024 * 1024) + "M]");
				}

				if (dir == null) {
					return MapResult.mapError(RspCode.R99999, "上传失败：dir 参数为空");
				}

				if (!dir.endsWith("/")) {
					dir = dir + "/";
				}

				String uploadFileName = multipartFile.getOriginalFilename();
				String fileName = System.currentTimeMillis() + "_" + DateUtils.getRandom(6) + suffix;
				;

				String path = WebUtils.getUploadImageDirectory() + dir;
				String filePath = path + fileName;

				setMkdir(path);

				multipartFile.transferTo(new File(filePath));

				String weburl = WebUtils.getUploadImageWeb() + dir + fileName;

				Upload entity = new Upload();
				entity.setFileName(fileName);
				entity.setUploadFileName(uploadFileName);
				entity.setWebUrl(weburl);
				entity.setUriPath(filePath);
				// 类型，0图片，1视频，2音频
				entity.setStype(fileType(suffix));
				entity.setFileSize(Double.valueOf(fileSize));

				uploadService.save(entity);

				return MapResult.mapOK(entity, "OK");
			} catch (Exception e) {
				e.printStackTrace();
				return MapResult.mapOK(e.getMessage(), "OK");
			}
		}

		return MapResult.mapError("请选择上次的图片");
	}

	/*
	 * @RequestMapping("base64")
	 * 
	 * @ResponseBody public Map<String, Object>
	 * fileUploadBase64(@RequestParam("file") MultipartFile
	 * multipartFile,HttpServletRequest request){
	 * 
	 *//** 判断文件是否为空,空直接返回上传错误 **//*
								 * if(!multipartFile.isEmpty()){ try{ //获得文件后缀名
								 * String suffix =
								 * multipartFile.getOriginalFilename().substring
								 * (multipartFile.getOriginalFilename().
								 * lastIndexOf(".")); if(suffix.equals(".jpg")
								 * || suffix.equals(".gif") ||
								 * suffix.equals(".png")) {//检测图片类型
								 * if(multipartFile.getSize() > 1000000) {
								 * return MapResult.mapError("上传失败：文件大小不能超过1M");
								 * }
								 * 
								 * BASE64Encoder encoder = new BASE64Encoder();
								 * // 通过base64来转化图片 String data =
								 * encoder.encode(multipartFile.getBytes());
								 * 
								 * String
								 * image="data:image/"+suffix.substring(1)+
								 * ";base64,"+data;
								 * 
								 * return MapResult.mapOK(image,"OK"); }else{
								 * return
								 * MapResult.mapError("上传失败：图片类型为:jpg、gif、png");
								 * }
								 * 
								 * if(suffix.equals(".jpg") ||
								 * suffix.equals(".gif") ||
								 * suffix.equals(".png")) {//检测图片类型
								 * if(file.getSize() > 1000000) { imageCode =
								 * "-1"; //throw new
								 * Exception("上传失败：文件大小不能超过1M"); }else { try {
								 * //将上传的图片转换成base64编码字符串 imageCode =
								 * "data:image/gif;base64," +
								 * ImageUtil.encodeImageToStr(file.
								 * getInputStream()); } catch (IOException e) {
								 * e.printStackTrace(); } } }
								 * 
								 * // String filePath=request.getSession().
								 * getServletContext().getRealPath("/")+
								 * "upload/"+multipartFile.getOriginalFilename()
								 * ;
								 * 
								 * //multipartFile.transferTo(new
								 * File(filePath)); }catch(Exception e){
								 * e.printStackTrace(); }
								 * 
								 * 
								 * }
								 * 
								 * return MapResult.mapError("请选择上次图片"); }
								 */

	private Integer fileType(String contentType) {
		// txt,rar,zip,doc,docx,xls,xlsx,jpg,jpeg,gif,png,swf,wmv,avi,wma,mp3,mid

		if ("jpg,jpeg,gif,png".contains(contentType)) {
			return 0;
		} else if ("swf,wmv,avi,wma".contains(contentType)) {
			return 1;
		} else if ("mp3,mid".contains(contentType)) {
			return 2;
		} else if ("txt,doc,docx,xls,xlsx".contains(contentType)) {
			return 3;
		} else if ("rar,zip".contains(contentType)) {
			return 4;
		} else {
			return 99;
		}
	}

	private boolean isValid(String contentType, String... allowTypes) {
		if (null == contentType || "".equals(contentType)) {
			return false;
		}
		for (String type : allowTypes) {
			if (contentType.indexOf(type) > -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 替换html中的base64图片数据为实际图片
	 * 
	 * @param html
	 * @param fileRoot
	 *            本地路径
	 * @param serRoot
	 *            服务器路径
	 * @return
	 */
	/*
	 * public static String replaceBase64Image(String html,String
	 * fileRoot,String serRoot){ File file = new File(fileRoot);
	 * if(!file.exists()){//文件根目录不存在时创建 new File(fileRoot).mkdirs(); } String
	 * htmlContent = html; Pattern pattern =
	 * Pattern.compile("\\<img[^>]*src=\"data:image/[^>]*>"); Matcher matcher =
	 * pattern.matcher(html); GUIDUtils.init(); while(matcher.find()){
	 * //找出base64图片元素 String str = matcher.group(); String src =
	 * ExStringUtils.substringBetween(str, "src=\"", "\"");//src="..." String
	 * ext = ExStringUtils.defaultIfEmpty(ExStringUtils.substringBetween(str,
	 * "data:image/", ";"), "jpg");//图片后缀 String base64ImgData =
	 * ExStringUtils.substringBetween(str, "base64,", "\"");//图片数据
	 * if(ExStringUtils.isNotBlank(ext)&&ExStringUtils.isNotBlank(base64ImgData)
	 * ){ //data:image/gif;base64,base64编码的gif图片数据
	 * //data:image/png;base64,base64编码的png图片数据
	 * if("jpeg".equalsIgnoreCase(ext)){//data:image/jpeg;base64,
	 * base64编码的jpeg图片数据 ext = "jpg"; } else
	 * if("x-icon".equalsIgnoreCase(ext)){//data:image/x-icon;base64,
	 * base64编码的icon图片数据 ext = "ico"; } String fileName =
	 * GUIDUtils.buildMd5GUID(false)+"."+ext;//待存储的文件名 String filePath =
	 * fileRoot+File.separator+fileName;//图片路径 try {
	 * convertBase64DataToImage(base64ImgData, filePath);//转成文件 String serPath =
	 * serRoot+fileName;//服务器地址 htmlContent = htmlContent.replace(src,
	 * serPath);//替换src为服务器地址 } catch (IOException e) { e.printStackTrace(); } }
	 * } return htmlContent; }
	 */
	/**
	 * 把base64图片数据转为本地图片
	 * 
	 * @param base64ImgData
	 * @param filePath
	 * @throws IOException
	 */
	public static void convertBase64DataToImage(String base64ImgData, String filePath) throws IOException {
		BASE64Decoder d = new BASE64Decoder();
		byte[] bs = d.decodeBuffer(base64ImgData);
		FileOutputStream os = new FileOutputStream(filePath);
		os.write(bs);
		os.close();
	}

	private boolean setMkdir(String path) {
		File dirFile = null;
		try {
			dirFile = new File(path);
			if (!(dirFile.exists()) && !(dirFile.isDirectory())) {
				return dirFile.mkdirs();
				/*
				 * if (creadok) { System.out.println( " ok:创建文件夹成功！ " ); } else
				 * { // System.out.println( " err:创建文件夹失败！ " ); }
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println(e);
			return false;
		}

		return true;
	}
}
