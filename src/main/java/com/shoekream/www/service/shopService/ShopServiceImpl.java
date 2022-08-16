package com.shoekream.www.service.shopService;

import com.shoekream.www.domain.shopVO.ShopVO;
import com.shoekream.www.handler.FTPhandler;
import com.shoekream.www.repository.shopDAO.ShopDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired private ShopDAO shopDAO;

    @Override
    public List<ShopVO> getItems() {
        return shopDAO.selectItems();
    }

    @Override @Transactional
    public int postItem(ShopVO shopVO, MultipartFile[] files) throws Exception {

        // todo /진행 중/ 데이터 검증 validation
        if(shopVO == null) {
            throw new Exception("[ERROR - POST] ShopVO = Null");
        }
        if(shopDAO.checkModelNumberDuple(shopVO.getModelNumber()) > 0) {
            throw new Exception("[ERROR - POST] 이미 등록된 모델번호");
        }
//        this.toCheckFileLengtnValidation(shopVO.getModelNumber(), 50);
        this.toCheckImageFileValidation(files);

        FTPhandler ftp = new FTPhandler();

        // todo /완료/ FTP 서버에 이미지 업로드 후 리스트에 경로 넣기
        ArrayList<String> urlList = new ArrayList<>();
        boolean ftpUploadFailed = true;
        for (MultipartFile file : files) {
            String fileUrl = ftp.upload(file);
            if(fileUrl != null) {
                urlList.add(fileUrl);
                ftpUploadFailed = false;
            }else {
                ftpUploadFailed = true;
                break;
            }
        }

        // todo /완료/ 이미지 업로드 도중 실패했을 경우 앞에서 업로드 된 파일 삭제 로직
        if(ftpUploadFailed == true) {
            ftp.deleteUnfinishedFiles();
            ftp.disconnect();
            return 0;
        }

        ftp.disconnect();
        shopDAO.insertProduct(shopVO);
        for (String url : urlList) {
            ShopVO tmp = new ShopVO();
            tmp.setModelNumber(shopVO.getModelNumber());
            tmp.setFileName(url);
            shopDAO.insertImage(tmp);
        }
        return shopVO.getItemNo();
    }

    @Override @Transactional
    public int putItems(ShopVO shopVO, MultipartFile[] files) throws Exception {

        // todo /진행 중/ data validation
        if(shopVO == null) {
            throw new Exception("[ERROR - PUT] ShopVO = Null");
        }

        FTPhandler ftp = new FTPhandler();

        if(files != null) {
            this.toCheckImageFileValidation(files);

            ArrayList<String> urlList = new ArrayList<>();
            boolean ftpUploadFailed = true;
            for (MultipartFile file : files) {
                String fileUrl = ftp.upload(file);
                if(fileUrl != null) {
                    urlList.add(fileUrl);
                    ftpUploadFailed = false;
                }else {
                    ftpUploadFailed = true;
                    break;
                }
            }
            // todo /완료/ 이미지 업로드 도중 실패했을 경우 앞에서 업로드 된 파일 삭제 로직
            if(ftpUploadFailed == true) {
                ftp.deleteUnfinishedFiles();
                ftp.disconnect();
                return 0;
            }
        }

        /**
         * todo 1. deleteImages != null 변수 받아서 FTP + 디비 url 삭제
         * todo 2. ShopVO 내용 디비에 update로 덮어씌우기
         */

//        if(shopVO.getDeleteImages() != null) {
//            List<ShopVO> imageUrlList = shopDAO.selectImageUrlWithId(shopVO.getImageId());
//            if(ftp.deleteImageFiles(imageUrlList)) {
//                if(shopDAO.deleteImageWithModelNumber(imageList) > 0) {
//                    shopDAO.deleteItem(pno);
//                }
//            }
//        }



        return 0;
    }

    @Override @Transactional
    public int removeItem(int pno) throws Exception {

        // todo /진행 중/ data validation
        if(shopDAO.selectItemsCount(pno) > 0){
            throw new Exception("거래 내역이 있는 Product");
        }

        // todo /완료/ 프로덕트 삭제시 서버에 저장된 이미지 + 디비 url 동시 삭제
        FTPhandler ftp = new FTPhandler();
        int result = 0;
        String modelNumber = shopDAO.selectModelNumber(pno);
        if(modelNumber != null) {
            List<ShopVO> imageList = shopDAO.selectImageIdAndUrl(modelNumber);
            if(imageList.size() > 0) {
                if(ftp.deleteImageFiles(imageList)) {
                    if(shopDAO.deleteImageWithModelNumber(imageList) > 0) {
                        shopDAO.deleteItem(pno);
                    }
                }
            }
        }
        return result;
    }


    private void toCheckImageFileValidation(MultipartFile[] files) throws Exception {
        for (MultipartFile file : files) {
            if(!file.getContentType().contains("image/")) {
                throw new Exception("[ERROR] 이미지 파일이 아님");
            }
        }
    }

    private void toCheckFileLengtnValidation(String value, int maxLength) throws Exception {
        if(value != null && value.length() > 50) {
            throw new Exception();
        }
    }
}
