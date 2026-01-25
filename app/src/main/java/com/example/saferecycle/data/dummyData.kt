package com.example.saferecycle.data

val emptyWasteList = mutableListOf<Waste>()

val dummyCategories = mutableListOf(
    Category(
        id = 1,
        name = "Organic",
        imagePath = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcShrpwIrl1yqleJWOOKz37uPxRMmNkI9e8deU4AGgKRlbwu4fIv"
    ),
    Category(
        id = 2,
        name = "Plastic",
        imagePath = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTK5nXLSzAqErtGn_qoUvmAzxQ7rXkTHNpWrUo6R1e13Hht0ZLG"
    ),
    Category(
        id = 3,
        name = "Metal",
        imagePath = "https://cdn-icons-png.flaticon.com/128/8134/8134449.png"
    ),
    Category(
        id = 4,
        name = "Glass",
        imagePath = "https://cdn-icons-png.freepik.com/512/4246/4246817.png"
    ),
    Category(
        id = 5,
        name = "Paper",
        imagePath = "https://cdn-icons-png.flaticon.com/512/737/737804.png"
    ),
    Category(
        id = 6,
        name = "Textile",
        imagePath = "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTtk-GfUMrASOkekz7Dh9f8HqJgy_G0F0Yt6ebD1FFnSQQn0n33"
    ),
    Category(
        id = 7,
        name = "Hazardous",
        imagePath = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj3X4DPmPr0DSagT7OvGPtAdD6VBn7mXdwSw&s"
    ),
    Category(
        id = 8,
        name = "E-Waste",
        imagePath = "https://cdn-icons-png.flaticon.com/128/11649/11649018.png"
    ),
    Category(
        id = 9,
        name = "Battery",
        imagePath = "https://static.vecteezy.com/system/resources/thumbnails/002/846/995/small_2x/battery-cell-concepts-vector.jpg"
    ),
    Category(
        id = 10,
        name = "Styrofoam",
        imagePath = "https://cdn-icons-png.flaticon.com/512/3765/3765611.png"
    ),
    Category(
        id = 11,
        name = "Mixed Waste",
        imagePath = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQ1OsBfssqoUEgogDKLxpu67ipRxGOG2WJb7j1UXJMgGN3SWshX"
    ),
    Category(
        id = 12,
        name = "Other",
        imagePath = "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTcILyeqvMcg1lvgvbLKqYjyAPKj_vYWGpGNFIeG9wkIFq6Y0H1"
    ),
)

val dummyWastes = mutableListOf(
    Waste(
        id = 1,
        name = "Can",
        imagePath = "https://cdn.pixabay.com/photo/2016/08/05/23/16/coca-cola-1573554_1280.jpg",
        category = Category(
            id = 3,
            name = "Metal",
            imagePath = "https://cdn-icons-png.flaticon.com/128/8134/8134449.png"
        ),
    ),
    Waste(
        id = 2,
        name = "Plastic Bottle",
        imagePath = "https://media.istockphoto.com/id/172715253/photo/bottle-of-water.jpg?s=612x612&w=0&k=20&c=hyKU4mWql3ei8UGICIRl8ys6L-iAfzo4BlNd_gnisVw=",
        category = Category(
            id = 2,
            name = "Plastic",
            imagePath = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTK5nXLSzAqErtGn_qoUvmAzxQ7rXkTHNpWrUo6R1e13Hht0ZLG"
        ),
    ),
    Waste(
        id = 3,
        name = "Food Waste",
        imagePath = "https://www.shutterstock.com/image-photo/sorted-kitchen-food-waste-paper-260nw-2628920935.jpg",
        category = Category(
            id = 1,
            name = "Organic",
            imagePath = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcShrpwIrl1yqleJWOOKz37uPxRMmNkI9e8deU4AGgKRlbwu4fIv"
        )
    ),
    Waste(
        id = 4,
        name = "Wine Bottle",
        imagePath = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4gmH2nFfn-VDFGvpiY35BQEf1X61_LRxc2Q&s",
        category = Category(
            id = 4,
            name = "Glass",
            imagePath = "https://cdn-icons-png.freepik.com/512/4246/4246817.png"
        ),
    ),
    Waste(
        id = 1,
        name = "Can",
        imagePath = "https://cdn.pixabay.com/photo/2016/08/05/23/16/coca-cola-1573554_1280.jpg",
        category = Category(
            id = 3,
            name = "Metal",
            imagePath = "https://cdn-icons-png.flaticon.com/128/8134/8134449.png"
        ),
    ),
    Waste(
        id = 2,
        name = "Plastic Bottle",
        imagePath = "https://media.istockphoto.com/id/172715253/photo/bottle-of-water.jpg?s=612x612&w=0&k=20&c=hyKU4mWql3ei8UGICIRl8ys6L-iAfzo4BlNd_gnisVw=",
        category = Category(
            id = 2,
            name = "Plastic",
            imagePath = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTK5nXLSzAqErtGn_qoUvmAzxQ7rXkTHNpWrUo6R1e13Hht0ZLG"
        ),
    ),
    Waste(
        id = 3,
        name = "Food Waste",
        imagePath = "https://www.shutterstock.com/image-photo/sorted-kitchen-food-waste-paper-260nw-2628920935.jpg",
        category = Category(
            id = 1,
            name = "Organic",
            imagePath = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcShrpwIrl1yqleJWOOKz37uPxRMmNkI9e8deU4AGgKRlbwu4fIv"
        )
    ),
    Waste(
        id = 4,
        name = "Wine Bottle",
        imagePath = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4gmH2nFfn-VDFGvpiY35BQEf1X61_LRxc2Q&s",
        category = Category(
            id = 4,
            name = "Glass",
            imagePath = "https://cdn-icons-png.freepik.com/512/4246/4246817.png"
        ),
    ),
    Waste(
        id = 1,
        name = "Can",
        imagePath = "https://cdn.pixabay.com/photo/2016/08/05/23/16/coca-cola-1573554_1280.jpg",
        category = Category(
            id = 3,
            name = "Metal",
            imagePath = "https://cdn-icons-png.flaticon.com/128/8134/8134449.png"
        ),
    ),
    Waste(
        id = 2,
        name = "Plastic Bottle",
        imagePath = "https://media.istockphoto.com/id/172715253/photo/bottle-of-water.jpg?s=612x612&w=0&k=20&c=hyKU4mWql3ei8UGICIRl8ys6L-iAfzo4BlNd_gnisVw=",
        category = Category(
            id = 2,
            name = "Plastic",
            imagePath = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTK5nXLSzAqErtGn_qoUvmAzxQ7rXkTHNpWrUo6R1e13Hht0ZLG"
        ),
    ),
    Waste(
        id = 3,
        name = "Food Waste",
        imagePath = "https://www.shutterstock.com/image-photo/sorted-kitchen-food-waste-paper-260nw-2628920935.jpg",
        category = Category(
            id = 1,
            name = "Organic",
            imagePath = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcShrpwIrl1yqleJWOOKz37uPxRMmNkI9e8deU4AGgKRlbwu4fIv"
        )
    ),
    Waste(
        id = 4,
        name = "Wine Bottle",
        imagePath = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4gmH2nFfn-VDFGvpiY35BQEf1X61_LRxc2Q&s",
        category = Category(
            id = 4,
            name = "Glass",
            imagePath = "https://cdn-icons-png.freepik.com/512/4246/4246817.png"
        ),
    )
)