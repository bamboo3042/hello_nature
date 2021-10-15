package com.hellonature.hellonature_back.service;

import com.hellonature.hellonature_back.model.entity.Magazine;
import com.hellonature.hellonature_back.model.entity.Product;
import com.hellonature.hellonature_back.model.enumclass.Flag;
import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.Pagination;
import com.hellonature.hellonature_back.model.network.request.MagazineApiRequest;
import com.hellonature.hellonature_back.model.network.response.MagazineApiResponse;
import com.hellonature.hellonature_back.model.network.response.MagazineDetailResponse;
import com.hellonature.hellonature_back.repository.LikeRepository;
import com.hellonature.hellonature_back.repository.MagazineRepository;
import com.hellonature.hellonature_back.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MagazineService {

//    @PersistenceUnit
//    EntityManagerFactory emf;

    private final EntityManager em;
    private final MagazineRepository magazineRepository;
    private final ProductRepository productRepository;
    private final LikeRepository likeRepository;
    private final FileService fileService;


    public Header<MagazineApiResponse> create(Header<MagazineApiRequest> request, List<MultipartFile> multipartFiles){
        List<String> pathList = fileService.imagesUploads(multipartFiles, "magazine");
        MagazineApiRequest magazineApiRequest = request.getData();

        Magazine magazine = Magazine.builder()
                .showFlag(magazineApiRequest.getShowFlag())
                .title(magazineApiRequest.getTitle())
                .img(pathList.get(0))
                .des(magazineApiRequest.getDes())
                .content(magazineApiRequest.getContent())
                .type(magazineApiRequest.getType())
                .cookTime(magazineApiRequest.getCookTime())
                .cookKcal(magazineApiRequest.getCookKcal())
                .cookLevel(magazineApiRequest.getCookLevel())
                .cookIngredient(magazineApiRequest.getCookIngredient())
                .build();
        magazineRepository.save(magazine);
        return Header.OK();
    }


    public Header<MagazineApiResponse> read(Long id) {
        return magazineRepository.findById(id)
                .map(magazine -> response(magazine))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("No data")
                );
    }


    public Header<MagazineApiResponse> update(Header<MagazineApiRequest> request, List<MultipartFile> multipartFiles) {
        List<String> pathList = fileService.imagesUploads(multipartFiles, "magazine");
        MagazineApiRequest magazineApiRequest = request.getData();
        Optional<Magazine> optional = magazineRepository.findById(magazineApiRequest.getIdx());
        return optional.map(magazine -> {
                    magazine.setShowFlag(magazineApiRequest.getShowFlag());
                    magazine.setImg(pathList.get(0));
                    magazine.setTitle(magazineApiRequest.getTitle());
                    magazine.setDes(magazineApiRequest.getDes());
                    magazine.setContent(magazineApiRequest.getContent());
                    magazine.setType(magazineApiRequest.getType());
                    magazine.setCookTime(magazineApiRequest.getCookTime());
                    magazine.setCookKcal(magazineApiRequest.getCookKcal());
                    magazine.setCookLevel(magazineApiRequest.getCookLevel());
                    magazine.setCookIngredient(magazineApiRequest.getCookIngredient());

                    return magazine;
                }).map(magazine -> magazineRepository.save(magazine))
                .map(magazine -> response(magazine))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }


    public Header delete(Long id) {
        Optional<Magazine> optional = magazineRepository.findById(id);

        return optional.map(magazine -> {
            magazineRepository.delete(magazine);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private MagazineApiResponse response(Magazine magazine){
        MagazineApiResponse magazineApiResponse = MagazineApiResponse.builder()
                .idx(magazine.getIdx())
                .showFlag(magazine.getShowFlag())
                .img(magazine.getImg())
                .title(magazine.getTitle())
                .des(magazine.getDes())
                .content(magazine.getContent())
                .type(magazine.getType())
                .cookTime(magazine.getCookTime())
                .cookKcal(magazine.getCookKcal())
                .cookLevel(magazine.getCookLevel())
                .cookIngredient(magazine.getCookIngredient())
                .build();
        System.out.println(magazineApiResponse);
        return magazineApiResponse;
    }

    public Header<List<MagazineApiResponse>> list(Long cateIdx, String title, String dateStart, String dateEnd, Integer startPage){
        String jpql = "select m from Magazine m";
        boolean check = false;

        if(cateIdx != null || title != null || dateStart != null || dateEnd != null){
            jpql += " where";
            if(cateIdx != null){
                jpql += " cate_idx = :cateIdx";
                check = true;
            }
            if (title != null){
                if (check) jpql += " and";
                jpql += " title like :title";
                check = true;
            }
            if (dateStart != null){
                if(check) jpql += " and";
                jpql += " TO_char(regdate, 'YYYY-MM-DD') >= :dateStart";
                check = true;
            }
            if(dateEnd != null){
                if (check) jpql += " and";
                jpql += " TO_char(regdate, 'YYYY-MM-DD') <= :dateEnd";
            }
        }

        jpql += " order by idx desc";

        System.out.println(jpql);

//        EntityManager em = emf.createEntityManager();

        TypedQuery<Magazine> query = em.createQuery(jpql, Magazine.class);
        if (cateIdx != null) query = query.setParameter("cateIdx", cateIdx);
        if (title != null) query = query.setParameter("title", "%"+title+"%");
        if (dateStart != null) query = query.setParameter("dateStart", dateStart);
        if (dateEnd != null) query = query.setParameter("dateEnd", dateEnd);

        List<Magazine> result = query.getResultList();
        int count = 10;
        Integer start = count * startPage;
        Integer end = Math.min(result.size(), start + count);

        Pagination pagination = new Pagination().builder()
                .totalPages(result.size() / count)
                .currentPage(startPage)
                .build();

        List<MagazineApiResponse> newList = new ArrayList<>();
        for (Magazine magazine: result.subList(start, end)) {
            newList.add(response(magazine));
        }

        return Header.OK(newList, pagination);
    }

    @Transactional
    public Header<MagazineDetailResponse> detail(Long idx){
        Optional<Magazine> optional = magazineRepository.findById(idx);
        Magazine magazine = optional.get();

        List<Long> list1 = Arrays.asList(magazine.getIngreList().split("-")).stream().map(Long::parseLong).collect(Collectors.toList());
        List<Product> ingreList = productRepository.findAllByIdxIn(list1);
        List<Long> list2 = Arrays.asList(magazine.getRelList().split("-")).stream().map(Long::parseLong).collect(Collectors.toList());
        List<Product> relList = productRepository.findAllByIdxIn(list2);

        MagazineDetailResponse magazineDetailResponse = MagazineDetailResponse.builder()
                .idx(magazine.getIdx())
                .img(magazine.getImg())
                .title(magazine.getTitle())
                .des(magazine.getDes())
                .content(magazine.getContent())
                .like(likeRepository.countByMagazine(magazine))
                .likeFlag(likeRepository.findByMagazine(magazine).isPresent() ? Flag.TRUE : Flag.FALSE)
                .type(magazine.getType())
                .cookTime(magazine.getCookTime())
                .cookKcal(magazine.getCookKcal())
                .cookLevel(magazine.getCookLevel())
                .cookIngredient(magazine.getCookIngredient())
                .regdate(magazine.getRegdate())
                .build();

        for (Product product: ingreList) {
            magazineDetailResponse.addIngreList(product.getIdx(), product.getName(), product.getImg1(), product.getNetPrice(), product.getSalePrice(), product.getBestFlag());
        }

        for (Product product: relList){
            magazineDetailResponse.addRelList(product.getIdx(), product.getName(), product.getImg1(), product.getNetPrice(), product.getSalePrice(), product.getBestFlag());
        }

        return Header.OK(magazineDetailResponse);
    }
}

