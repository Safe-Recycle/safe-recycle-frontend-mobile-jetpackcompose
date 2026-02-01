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

val dummyWastes: MutableList<Waste> = mutableListOf(
    Waste(
        id = 1,
        name = "Can",
        imagePath = "https://cdn.pixabay.com/photo/2016/08/05/23/16/coca-cola-1573554_1280.jpg",
        category = Category(
            id = 3,
            name = "Metal",
            imagePath = "https://cdn-icons-png.flaticon.com/128/8134/8134449.png"
        ),
        isReusable = false,
        isRecyclable = true,
        isHazardous = false,
        description = "A metal can commonly used for beverages or canned food. It is lightweight, durable, and usually made of aluminum or steel.",
        recycleTips = """
### First Choice: Recycle It (Best Option)

Metal cans are one of the most valuable recyclable materials. Aluminum and steel can be recycled repeatedly without losing quality.

### Before Recycling

Before putting the can into the recycling bin, make sure it is clean and safe.

Steps:
1. Empty the can completely  
2. Rinse it briefly to remove leftover liquid or food  
3. If possible, lightly crush the can to save space  

> Sharp edges can appear after crushing. Handle with care.

### How Metal Recycling Helps

Recycling metal cans:
- Saves a large amount of energy compared to producing new metal
- Reduces mining activities that damage the environment
- Lowers greenhouse gas emissions

### What to Avoid

- Do not recycle cans filled with food or liquid  
- Do not mix metal cans with hazardous waste  
- Avoid throwing cans into general trash if recycling is available  

♻️ Clean metal cans greatly improve recycling efficiency.
"""
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
        isReusable = true,
        isRecyclable = true,
        isHazardous = false,
        description = "A bottle made out of plastic. It is shaped like a bottle, holds liquids, and does bottle-related things such as being opened or closed.",
        recycleTips = """
### First Choice: Reuse It (Best Option)

Before throwing it away, see if the item can be reused.

Easy Reuse Ideas:
- Refill with water if it is a sturdy bottle and **only for short-term use**
- Store cleaning liquids, oil, or detergents
- Cut it to make: Plant pots, Seed starters, Drip irrigation tools
- Use it to organize small items such as: Screws, Pens, Wires

> If the bottle is crushed, very thin, or smells bad, skip reuse and recycle it.

### How to Recycle a Plastic Bottle Properly

Most plastic bottles are recyclable, especially those marked **PET**, **PETE**, or **#1**.

Steps
1. Empty the bottle completely  
2. Rinse it quickly with water to remove liquid or food residue  
3. Remove the cap if your local recycling rules require it  
4. Some areas allow caps on, others do not  
4. Lightly crush the bottle to save space  
5. Place it in the plastic recycling bin  

⚠️ Avoid mixing plastic bottles with food waste or dirty trash, as this can contaminate and ruin recycling batches.
    """
    ),
    Waste(
        id = 3,
        name = "Food Waste",
        imagePath = "https://www.shutterstock.com/image-photo/sorted-kitchen-food-waste-paper-260nw-2628920935.jpg",
        category = Category(
            id = 1,
            name = "Organic",
            imagePath = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcShrpwIrl1yqleJWOOKz37uPxRMmNkI9e8deU4AGgKRlbwu4fIv"
        ),
        isReusable = false,
        isRecyclable = true,
        isHazardous = false,
        description = "Organic waste from kitchens such as leftover food, vegetable scraps, and fruit peels that naturally decompose over time.",
        recycleTips = """
### Best Option: Compost It

Food waste is biodegradable and should never go into recycling bins for plastic, metal, or glass.

Common Food Waste Includes:
- Leftover rice or cooked food  
- Vegetable scraps and peels  
- Fruit skins and cores  
- Expired food (without packaging)

### How to Handle Food Waste Properly

Steps:
1. Separate food waste from plastic, metal, or paper packaging  
2. Place it in a compost bin or organic waste container  
3. Keep the container closed to prevent odor and pests  

### Composting Benefits

Composting food waste:
- Reduces methane emissions from landfills  
- Produces nutrient-rich compost for plants  
- Helps close the natural food cycle  

### What to Avoid

⚠️ Do not compost:
- Plastic wrappers  
- Oil in large quantities  
- Chemical-contaminated food  

> Mixing food waste with recyclable materials can contaminate and ruin entire recycling batches.
"""
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
        isReusable = true,
        isRecyclable = true,
        isHazardous = false,
        description = "A glass bottle used for storing wine. Glass is non-reactive and can be recycled indefinitely without losing quality.",
        recycleTips = """
### First Choice: Reuse It (Recommended)

Glass bottles are durable and safe for reuse before recycling.

Reuse Ideas:
- Refill as a water bottle (for home use)
- Use as a decorative vase or lamp
- Store homemade oil, vinegar, or sauces

> Always wash thoroughly before reuse.

### How to Recycle a Glass Bottle

Glass can be recycled endlessly without quality loss.

Steps:
1. Empty the bottle completely  
2. Rinse to remove liquid residue  
3. Remove corks or caps  
4. Place the bottle in the glass recycling bin  

### Safety Notes

⚠️ Do not intentionally break glass bottles  
⚠️ Handle broken glass carefully to avoid injury  

### Why Glass Recycling Matters

- Reduces raw material extraction  
- Saves energy in glass production  
- Prevents injuries from broken glass in landfills  
"""
    ),
)