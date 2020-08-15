//package com.dotin.dotintasktwo.model;//package com.dotin.dotintasktwo.model;
//
//public enum LeaveType {
//    DAILY, HOURLY;
//}


//    @Query("select p from Posts p join p.categories pc where (:#{#posts.title} is null or " +
//            "p.title like concat('%',:#{#posts.title},'%')) and " +
//            "(coalesce(:#{#posts.categories},null) is null or " +
//            "pc in (:#{#posts.categories})) " +
//            "group by p.id having count (p.id) >= :num")
//    Page<Posts> findBySearch(Posts posts, @Param("num") Long size, Pageable pageable);

//    @Query(value = "SELECT A.IS_MUTUAL_AID FROM planex AS A
//            INNER JOIN planex_rel AS B ON A.PLANEX_ID=B.PLANEX_ID
//            WHERE B.GOOD_ID = :goodId",nativeQuery = true)
//
//            Boolean mutualAidFlag(@Param("goodId")Integer goodId);