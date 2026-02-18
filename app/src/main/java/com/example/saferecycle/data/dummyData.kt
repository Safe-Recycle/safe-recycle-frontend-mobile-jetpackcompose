package com.example.saferecycle.data

import com.example.saferecycle.data.model.Category
import com.example.saferecycle.data.model.User
import com.example.saferecycle.data.model.Waste

val dummyUser = User(
    id = 0,
    name = "Elma",
    email = "elmahanarokushou@gmail.com"
)

val emptyWasteList = mutableListOf<Waste>()

val dummyCategories = mutableListOf(
    Category(
        id = 1,
        name = "Organic",
        imageLink = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcShrpwIrl1yqleJWOOKz37uPxRMmNkI9e8deU4AGgKRlbwu4fIv"
    ),
    Category(
        id = 2,
        name = "Plastic",
        imageLink = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTK5nXLSzAqErtGn_qoUvmAzxQ7rXkTHNpWrUo6R1e13Hht0ZLG"
    ),
    Category(
        id = 3,
        name = "Metal",
        imageLink = "https://cdn-icons-png.flaticon.com/128/8134/8134449.png"
    ),
    Category(
        id = 4,
        name = "Glass",
        imageLink = "https://cdn-icons-png.freepik.com/512/4246/4246817.png"
    ),
    Category(
        id = 5,
        name = "Paper",
        imageLink = "https://cdn-icons-png.flaticon.com/512/737/737804.png"
    ),
    Category(
        id = 6,
        name = "Textile",
        imageLink = "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTtk-GfUMrASOkekz7Dh9f8HqJgy_G0F0Yt6ebD1FFnSQQn0n33"
    ),
    Category(
        id = 7,
        name = "Hazardous",
        imageLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj3X4DPmPr0DSagT7OvGPtAdD6VBn7mXdwSw&s"
    ),
    Category(
        id = 8,
        name = "E-Waste",
        imageLink = "https://cdn-icons-png.flaticon.com/128/11649/11649018.png"
    ),
    Category(
        id = 9,
        name = "Battery",
        imageLink = "https://static.vecteezy.com/system/resources/thumbnails/002/846/995/small_2x/battery-cell-concepts-vector.jpg"
    ),
    Category(
        id = 10,
        name = "Styrofoam",
        imageLink = "https://cdn-icons-png.flaticon.com/512/3765/3765611.png"
    ),
    Category(
        id = 11,
        name = "Mixed Waste",
        imageLink = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQ1OsBfssqoUEgogDKLxpu67ipRxGOG2WJb7j1UXJMgGN3SWshX"
    ),
    Category(
        id = 12,
        name = "Other",
        imageLink = "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTcILyeqvMcg1lvgvbLKqYjyAPKj_vYWGpGNFIeG9wkIFq6Y0H1"
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
            imageLink = "https://cdn-icons-png.flaticon.com/128/8134/8134449.png"
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
            imageLink = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTK5nXLSzAqErtGn_qoUvmAzxQ7rXkTHNpWrUo6R1e13Hht0ZLG"
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
            imageLink = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcShrpwIrl1yqleJWOOKz37uPxRMmNkI9e8deU4AGgKRlbwu4fIv"
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
        name = "Food Scraps",
        imagePath = "https://images.unsplash.com/photo-1605600659908-0ef719419d41",
        category = Category(
            id = 1,
            name = "Organic",
            imageLink = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcShrpwIrl1yqleJWOOKz37uPxRMmNkI9e8deU4AGgKRlbwu4fIv"
        ),
        isReusable = false,
        isRecyclable = true,
        isHazardous = false,
        description = "Organic waste such as leftover food, vegetable peels, and fruit scraps that can naturally decompose.",
        recycleTips = """
### Best Option: Compost It

Organic waste should be composted whenever possible instead of being thrown into general trash.

### How to Handle Organic Waste

Steps:
1. Separate food scraps from plastic or packaging  
2. Place them in an organic waste or compost bin  
3. Keep the container closed to avoid pests  

### Why Composting Matters

Composting:
- Reduces methane emissions from landfills
- Produces nutrient-rich soil
- Supports sustainable waste management

### What to Avoid

- Do not mix organic waste with plastic or metal  
- Avoid adding chemical-contaminated food  

🌱 Proper composting turns waste into valuable resources.
"""
    ),
    Waste(
        id = 5,
        name = "Plastic Bag",
        imagePath = "https://images.unsplash.com/photo-1582408921715-18e7806365c1",
        category = Category(
            id = 2,
            name = "Plastic",
            imageLink = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTK5nXLSzAqErtGn_qoUvmAzxQ7rXkTHNpWrUo6R1e13Hht0ZLG"
        ),
        isReusable = true,
        isRecyclable = true,
        isHazardous = false,
        description = "A thin plastic bag commonly used for shopping and packaging goods.",
        recycleTips = """
### First Choice: Reuse It

Plastic bags can often be reused multiple times before disposal.

Reuse Ideas:
- Grocery shopping
- Trash liners
- Storage for lightweight items

### Recycling Plastic Bags

Steps:
1. Ensure the bag is clean and dry  
2. Fold it neatly  
3. Return it to plastic collection points or drop-off bins  

### Environmental Impact

Plastic bags:
- Easily pollute oceans
- Harm wildlife
- Take hundreds of years to decompose

⚠️ Avoid single-use plastic whenever possible.
"""
    ),
    Waste(
        id = 6,
        name = "Can",
        imagePath = "https://cdn.pixabay.com/photo/2016/08/05/23/16/coca-cola-1573554_1280.jpg",
        category = Category(
            id = 3,
            name = "Metal",
            imageLink = "https://cdn-icons-png.flaticon.com/128/8134/8134449.png"
        ),
        isReusable = false,
        isRecyclable = true,
        isHazardous = false,
        description = "A metal can commonly used for beverages or canned food.",
        recycleTips = """
### First Choice: Recycle It (Best Option)

Metal cans are one of the most valuable recyclable materials.

### Before Recycling

Steps:
1. Empty the can completely  
2. Rinse to remove residue  
3. Lightly crush to save space  

### How Metal Recycling Helps

- Saves energy
- Reduces mining
- Lowers emissions

### What to Avoid

- Food-contaminated cans
- Mixing with hazardous waste

♻️ Clean cans improve recycling efficiency.
"""
    ),
    Waste(
        id = 7,
        name = "Wine Bottle",
        imagePath = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4gmH2nFfn-VDFGvpiY35BQEf1X61_LRxc2Q&s",
        category = Category(
            id = 4,
            name = "Glass",
            imageLink = "https://cdn-icons-png.freepik.com/512/4246/4246817.png"
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
    Waste(
        id = 8,
        name = "Newspaper",
        imagePath = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKWTjZm9pkfoubFEViK_MAVrqHtcVkKTFQ1w&s",
        category = Category(
            id = 5,
            name = "Paper",
            imageLink = "https://cdn-icons-png.flaticon.com/512/737/737804.png"
        ),
        isReusable = true,
        isRecyclable = true,
        isHazardous = false,
        description = "Printed paper used for news and information.",
        recycleTips = """
### First Choice: Reuse It

Paper can often be reused before recycling.

Reuse Ideas:
- Cleaning windows
- Wrapping fragile items

### Recycling Paper

Steps:
1. Keep paper dry  
2. Remove plastic or tape  
3. Place in paper bin  

### Why Recycle Paper

- Saves trees
- Reduces water usage
- Lowers energy consumption
"""
    ),
    Waste(
        id = 9,
        name = "Old Clothes",
        imagePath = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ7abV6Z0i96KtvvQa5IqgaRdxZHz4AQ-IpEQ&s",
        category = Category(
            id = 6,
            name = "Textile",
            imageLink = "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTtk-GfUMrASOkekz7Dh9f8HqJgy_G0F0Yt6ebD1FFnSQQn0n33"
        ),
        isReusable = true,
        isRecyclable = true,
        isHazardous = false,
        description = "Used clothing that is no longer worn.",
        recycleTips = """
### First Choice: Reuse or Donate

Textiles can often be reused instead of discarded.

Options:
- Donate wearable clothes
- Use as cleaning rags

### Textile Recycling

Steps:
1. Ensure clothes are clean  
2. Separate by material  
3. Drop at textile collection points  

Recycling textiles reduces landfill waste significantly.
"""
    ),
    Waste(
        id = 10,
        name = "Paint Can",
        imagePath = "https://c8.alamy.com/comp/C8XEWJ/empty-paint-spray-cans-used-for-graffiti-and-street-art-in-a-trash-C8XEWJ.jpg",
        category = Category(
            id = 7,
            name = "Hazardous",
            imageLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj3X4DPmPr0DSagT7OvGPtAdD6VBn7mXdwSw&s"
        ),
        isReusable = false,
        isRecyclable = false,
        isHazardous = true,
        description = "A container that once held chemical-based paint.",
        recycleTips = """
### Special Handling Required

Hazardous waste must not be mixed with regular trash.

Steps:
1. Keep container sealed  
2. Do not pour leftover paint into drains  
3. Bring to hazardous waste collection centers  

⚠️ Improper disposal can contaminate soil and water.
"""
    ),
    Waste(
        id = 11,
        name = "Old Smartphone",
        imagePath = "https://i.pcmag.com/imagery/articles/023e2NBigdO209c0MJJefYF-13..v1617731853.jpg",
        category = Category(
            id = 8,
            name = "E-Waste",
            imageLink = "https://cdn-icons-png.flaticon.com/128/11649/11649018.png"
        ),
        isReusable = false,
        isRecyclable = true,
        isHazardous = true,
        description = "Electronic devices containing metals and hazardous components.",
        recycleTips = """
### Do Not Throw in Trash

Electronic waste contains valuable and hazardous materials.

Steps:
1. Remove personal data  
2. Do not dismantle yourself  
3. Bring to certified e-waste centers  

Recycling e-waste recovers metals and prevents pollution.
"""
    ),
    Waste(
        id = 12,
        name = "Used Battery",
        imagePath = "https://www.brightminds.co.uk/cdn/shop/products/battery-d-size-brightminds_221.jpg?v=1591863848&width=500",
        category = Category(
            id = 9,
            name = "Battery",
            imageLink = "https://static.vecteezy.com/system/resources/thumbnails/002/846/995/small_2x/battery-cell-concepts-vector.jpg"
        ),
        isReusable = false,
        isRecyclable = true,
        isHazardous = true,
        description = "Used household batteries containing chemical substances.",
        recycleTips = """
### Special Disposal Required

Batteries contain chemicals that can leak and cause fires.

Steps:
1. Tape battery terminals  
2. Store in a dry container  
3. Bring to battery recycling points  

⚠️ Never throw batteries into regular trash.
"""
    ),
    Waste(
        id = 13,
        name = "Styrofoam Container",
        imagePath = "https://bibitbunga.com/wp-content/uploads/2020/10/rug-1602822595632-0.jpeg",
        category = Category(
            id = 10,
            name = "Styrofoam",
            imageLink = "https://cdn-icons-png.flaticon.com/512/3765/3765611.png"
        ),
        isReusable = false,
        isRecyclable = false,
        isHazardous = false,
        description = "Lightweight foam container used for food packaging.",
        recycleTips = """
### Reduce Usage

Styrofoam is difficult to recycle and harmful to the environment.

Steps:
1. Avoid single-use styrofoam  
2. Reuse only if clean and intact  
3. Dispose as general waste if recycling is unavailable  

Reducing styrofoam use is the best solution.
"""
    ),
    Waste(
        id = 14,
        name = "Snack Wrapper",
        imagePath = "https://media.istockphoto.com/id/1147024409/photo/after-party-finished-candy-full-of-wrapping-paper.jpg?s=612x612&w=0&k=20&c=ASxDbCSEeWdwkSIW1YWJXerN-eO3BsisTeM3QCeRImA=",
        category = Category(
            id = 11,
            name = "Mixed Waste",
            imageLink = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQ1OsBfssqoUEgogDKLxpu67ipRxGOG2WJb7j1UXJMgGN3SWshX"
        ),
        isReusable = false,
        isRecyclable = false,
        isHazardous = false,
        description = "Packaging made from mixed materials that are hard to separate.",
        recycleTips = """
### General Waste

Mixed waste cannot be easily recycled.

Steps:
1. Ensure no food remains  
2. Dispose in general trash  
3. Reduce usage when possible  

Choosing recyclable packaging helps reduce mixed waste.
"""
    ),
    Waste(
        id = 15,
        name = "Broken Household Item",
        imagePath = "https://www.foodstoragemoms.com/wp-content/uploads/2023/01/12-Ways-to-Reuse-Broken-Household-Items.jpeg",
        category = Category(
            id = 12,
            name = "Other",
            imageLink = "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTcILyeqvMcg1lvgvbLKqYjyAPKj_vYWGpGNFIeG9wkIFq6Y0H1"
        ),
        isReusable = false,
        isRecyclable = false,
        isHazardous = false,
        description = "Items that do not clearly fall into specific waste categories.",
        recycleTips = """
### Check Before Disposal

Some items may still be reusable or recyclable.

Steps:
1. Identify material if possible  
2. Separate recyclable parts  
3. Dispose responsibly  

When unsure, consult local waste guidelines.
"""
    )
)