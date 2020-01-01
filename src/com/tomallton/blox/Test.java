package com.tomallton.blox;

import java.io.ByteArrayInputStream;

import com.tomallton.blox.jsonparser.JsonParser;

public class Test {

    public static void main(String[] args) throws Exception {
        print("{\n" + 
                "    \"key\": \"GEN4_MAIN_QUEST_2\",\n" + 
                "    \"name\": \"&6Ignition of Space\",\n" + 
                "    \"description\": \"Thick black smoke can be seen above Woodburn. Go and see if you can help!\",\n" + 
                "    \"location\": \"Talk to the Pokemon Ranger at the Woodburn gym.\",\n" + 
                "    \"minimumlevel\": 5,\n" + 
                "    \"hideInQuestLog\": false,\n" + 
                "    \"allowRejoining\": true,\n" + 
                "    \"icon\": {\n" + 
                "        \"material\": \"BLAZE_POWDER\"\n" + 
                "    },\n" + 
                "    \"rewards\": [\n" + 
                "        \"&610000 Coins\",\n" + 
                "        \"&515 Tokens\",\n" + 
                "        \"&27000 Trainer Experience\",\n" + 
                "        \"&d3 PP Up\"\n" + 
                "    ],\n" + 
                "    \"npcs\": {\n" + 
                "        \"Pokemon_Ranger\": {\n" + 
                "            \"name\": \"&cPokemon Ranger\",\n" + 
                "            \"location\": {\n" + 
                "                \"world\": \"world\",\n" + 
                "                \"x\": 270.5,\n" + 
                "                \"y\": 68.0,\n" + 
                "                \"z\": 714.5,\n" + 
                "                \"yaw\": 255,\n" + 
                "                \"pitch\": 0\n" + 
                "            },\n" + 
                "            \"skin\": {\n" + 
                "                \"texture\": \"eyJ0aW1lc3RhbXAiOjE1NDM2MTA3ODExMDYsInByb2ZpbGVJZCI6IjkxOGEwMjk1NTlkZDRjZTZiMTZmN2E1ZDUzZWZiNDEyIiwicHJvZmlsZU5hbWUiOiJCZWV2ZWxvcGVyIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85NDY2NjQ2NzAyZDM4YmJiYjIwOGY3ZTUxMDRkYzExZjliYTYxMjBkYjQ0MjdjN2Q0M2M4NWMwNTI2ZmM3NzNkIn19fQ==\",\n" + 
                "                \"signature\": \"U/1O25TL/j6NLV3+0ibesGkrMFkQAPpWbNZGGjycyTA/3uluhkrd4NcoH+3JfJ7y1rlgMzOM/+BjzTNPc8DBvbCikgEcicU5Bsdrrt3q95ZkOFTN4yJEzGjok6cvH3or59LeBFdMArIYs6XUr9g1vRBjSns+MhB1tHKHKZ7jwEYfXdgt3vHQljt3FuefPyEtOjASMc+igOyrUH/YFdNP8G3vvW2+qKzIE6Nl53ZbvkchM7Qlnv1v5QWnNNUlkSwyr/8HSHRJ8HGPXiSJJMmkmal/+NhM3Ue2ZOAkedGyNrB4ANkazcuMdM8MsNsYTCakkb7jEg22sOh+s+BcNsiurrrmysePOJmsAhwImKUxj0EdwV7OLClle7kfN5VNrvMwmZhkCpBDP4kmhvOYT7jr7LE/zC8SEbuCZwlbjTVoW+nfQm7EkmnivYg5W5Tl6+r7AmYhfSmcdD3Lp7Ag0MX+tuF5yhzlm5kiuIodtorJ7VmVm0RwpOnH3/XPc/Pp/cZJvJlWyF2LnmHsL3MFRXZKU5q3Azf36wEDQada5Iy9SXvIHnTrvJ0msj03+kfWRNmO6DAkfAp+3r/vnkqK+m7ypxoZHoL/A61EvhlD7FO7xHkugEDvM4xaNveL3MTL75s0tfDPJtX2baPvVhj8kXL3X+BvMrv+pfkfFeeW5n+sI94=\"\n" + 
                "            }\n" + 
                "        },\n" + 
                "        \"Officer_Reynolds\": {\n" + 
                "            \"name\": \"&9Officer Reynolds\",\n" + 
                "            \"location\": {\n" + 
                "                \"world\": \"world\",\n" + 
                "                \"x\": 347.5,\n" + 
                "                \"y\": 43.0,\n" + 
                "                \"z\": 727.5,\n" + 
                "                \"yaw\": 270,\n" + 
                "                \"pitch\": 0\n" + 
                "            },\n" + 
                "            \"skin\": {\n" + 
                "                \"texture\": \"eyJ0aW1lc3RhbXAiOjE1NDM2MTQzMzc2NTMsInByb2ZpbGVJZCI6IjFjZjQ0OTMwODY1MTQ4NGE5ZmZjODI5YjlmNDg3NGE2IiwicHJvZmlsZU5hbWUiOiJJbmdBbmciLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg1ODNjMGI2NzYwOWNjZmRkMTk1YzUzYWZkMTVhNWVhN2NkMmRkNTAzMTEwYjA4OTE4MmE0NDE1NGVlZDlkNSJ9fX0=\",\n" + 
                "                \"signature\": \"vWXRYK0PI9hXfhHd0hIE00XVEGZEwDd1Rlf5Jw211GksN3wmtlP7sCqCBGmAPmrxjzQXnmGKR9OCPchwy+bfcd7dEMlxpvo9VzviRDYJCus1+1ClYGoHdUgn2FY8/9cYSJG5mh1AoehGktHh8ySnTaK5ZQP8NVKV/O4nfbHn0sRATgmtTY6VWHQx8mPz3FFghGZaxCrCOfGR2oru0VveODcUwsR/fLdDxDszcJ50eu4qU+SghuBso1pT2SRSNdQyXn9oUPQ/DT+0Ro/J53zWFKMtA/m98rEYQFdngtZiA1ttdPV/3xKTmYUoyAKX0II8NZyLbWbwZogwg4SO+gwOtubKe4OzJkGefKgfNYWxw3AsPVr1McbuLqMpAga58T0k2NHTkYhvyCTjJusbQB3sgeTymgiEef4rjfNGR0Ay9wZTKo2COd/Xtp1+pYm9+5471UUIsO2JqmM3j5BuR+5PTD5thj+LI1MC7DUXM/qYkoF7wS4RA71WcGkuCEYtNiUSXjOCX8sSasjoM4JBA+GRMWqifLQAj1ziJoU+b2NVDLi3S6Hej3GQatU1wCC0Wi893UP5/HObNGIfGHpdQsSxBSm7uPbboDl8qpSCJj9SapoRqEDLDyfjmxzp6K34rb/oVREUgka2DFrHQkuUH7cNc0RHA3hfHi3qpMT/MXb302o=\"\n" + 
                "            }\n" + 
                "        },\n" + 
                "        \"Wald_One\": {\n" + 
                "            \"name\": \"&cWald\",\n" + 
                "            \"location\": {\n" + 
                "                \"world\": \"world\",\n" + 
                "                \"x\": 301.5,\n" + 
                "                \"y\": 70.0,\n" + 
                "                \"z\": 766.5,\n" + 
                "                \"yaw\": 270,\n" + 
                "                \"pitch\": 26\n" + 
                "            },\n" + 
                "            \"skin\": {\n" + 
                "                \"texture\": \"eyJ0aW1lc3RhbXAiOjE1NDM2NjkxODg5MjcsInByb2ZpbGVJZCI6ImRkZWQ1NmUxZWY4YjQwZmU4YWQxNjI5MjBmN2FlY2RhIiwicHJvZmlsZU5hbWUiOiJEaXNjb3JkQXBwIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zZjNkNDE0NDE1ZGQ4NjhiYjNhYjQ0OWFjYmE0YzBmNmU4ZDJkODI3NThjM2I2ZGI0YjhhNWEyMTM5NzhhMDNlIn19fQ==\",\n" + 
                "                \"signature\": \"Op6qK22aGheey4G64GkaZxnp1z8jIjDPcq0XZ9ujHcPEexdizndDnlehz1SHIEtNe/LhLT2OxF+avIbEg5hjDovLzUbvgAWmgPhkKGOwzd1/8vnmRb7NyL9/4KvRXAeET2vR/ALkY689uBsjLw7e4hy7N71pGFTFcl+w1pAU4CKtYCOLZK2C5qOTezDNeRCvvYt4qrcfef13bJrop0NnCy/fC83ZwnpQMriBVjwIlzkrhScsXAvTu9K75wXAB83t9L7oJ9HpAq0EWzjCKDB0Tci3Lz+eA6Rnvx5v2O0J5NncmIVGVcT5cj1TuSFH1pFF1asIHHy8xIJanam5P2sZrlvLjXLIttlMM7LJjTcAh1q4LOjetUfgy7Yk/BemrcPIE2SfOKNdATLMnpiffCYRziRYyDM0S3FubeBCx98vBwPqMsMSTK+QBW+MS1OeOLP4KSnw2lO8o/yDtzZU3YKaPecD/G4quw1CxjbOzUQ82Ypi5XclGIl5W81ZEJSzlq5GkVd8ceVzif+080UCxKTRczVgJjN6aJM9WKJBc0bF38mdKzmKCBOvwtXF8zk/PsRCD0JxJ7dqgpnZlTFH1RS08Ct2fiktjT0CGphyOpSswnifnO98wygPuWrSmTbQ+dK3+caZ0w692d+AhYe2d2WSDXRzvAwlbF6w8Pom8ZqVlps=\"\n" + 
                "            }\n" + 
                "        },\n" + 
                "        \"Wald_Two\": {\n" + 
                "            \"name\": \"&cWald\",\n" + 
                "            \"location\": {\n" + 
                "                \"world\": \"world\",\n" + 
                "                \"x\": 338.5,\n" + 
                "                \"y\": 43.0,\n" + 
                "                \"z\": 724.5,\n" + 
                "                \"yaw\": 0,\n" + 
                "                \"pitch\": 0\n" + 
                "            },\n" + 
                "            \"skin\": {\n" + 
                "                \"texture\": \"eyJ0aW1lc3RhbXAiOjE1NDM2NjkxODg5MjcsInByb2ZpbGVJZCI6ImRkZWQ1NmUxZWY4YjQwZmU4YWQxNjI5MjBmN2FlY2RhIiwicHJvZmlsZU5hbWUiOiJEaXNjb3JkQXBwIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zZjNkNDE0NDE1ZGQ4NjhiYjNhYjQ0OWFjYmE0YzBmNmU4ZDJkODI3NThjM2I2ZGI0YjhhNWEyMTM5NzhhMDNlIn19fQ==\",\n" + 
                "                \"signature\": \"Op6qK22aGheey4G64GkaZxnp1z8jIjDPcq0XZ9ujHcPEexdizndDnlehz1SHIEtNe/LhLT2OxF+avIbEg5hjDovLzUbvgAWmgPhkKGOwzd1/8vnmRb7NyL9/4KvRXAeET2vR/ALkY689uBsjLw7e4hy7N71pGFTFcl+w1pAU4CKtYCOLZK2C5qOTezDNeRCvvYt4qrcfef13bJrop0NnCy/fC83ZwnpQMriBVjwIlzkrhScsXAvTu9K75wXAB83t9L7oJ9HpAq0EWzjCKDB0Tci3Lz+eA6Rnvx5v2O0J5NncmIVGVcT5cj1TuSFH1pFF1asIHHy8xIJanam5P2sZrlvLjXLIttlMM7LJjTcAh1q4LOjetUfgy7Yk/BemrcPIE2SfOKNdATLMnpiffCYRziRYyDM0S3FubeBCx98vBwPqMsMSTK+QBW+MS1OeOLP4KSnw2lO8o/yDtzZU3YKaPecD/G4quw1CxjbOzUQ82Ypi5XclGIl5W81ZEJSzlq5GkVd8ceVzif+080UCxKTRczVgJjN6aJM9WKJBc0bF38mdKzmKCBOvwtXF8zk/PsRCD0JxJ7dqgpnZlTFH1RS08Ct2fiktjT0CGphyOpSswnifnO98wygPuWrSmTbQ+dK3+caZ0w692d+AhYe2d2WSDXRzvAwlbF6w8Pom8ZqVlps=\"\n" + 
                "            }\n" + 
                "        }\n" + 
                "    },\n" + 
                "    \"visibility\": {\n" + 
                "        \"Pokemon_Ranger\": true,\n" + 
                "        \"Officer_Reynolds\": false,\n" + 
                "        \"Wald_One\": false,\n" + 
                "        \"Wald_Two\": false\n" + 
                "    },\n" + 
                "    \"ClickEntityStage\": [\n" + 
                "        \"Pokemon_Ranger\"\n" + 
                "    ],\n" + 
                "    \"CompletedOtherQuestProgressStage\": [\n" + 
                "        \"GEN4_MAIN_QUEST_1\"\n" + 
                "    ],\n" + 
                "    \"NotCompletedQuestProgressStage\": [\n" + 
                "        \n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"Pokemon Ranger\": \"Gym is closed due to emergency!\",\n" + 
                "            \"Pokemon Ranger\": \"If you are an able-bodied trainer…\",\n" + 
                "            \"Pokemon Ranger\": \"…please help fight the flames.\",\n" + 
                "            \"Pokemon Ranger\": \"If not, then what are you doing at a gym?!\",\n" + 
                "            \"Pokemon Ranger\": \"Find Officer Reynolds and help the effort!\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        0.1\n" + 
                "    ],\n" + 
                "    \"ObjectiveStage\": [\n" + 
                "        \"Run around the city and find Officer Reynolds\",\n" + 
                "        12\n" + 
                "    ],\n" + 
                "    \"DistanceTravelledStage\": [\n" + 
                "        40\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"Hello! Hello?!\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"DistanceTravelledStage\": [\n" + 
                "        40\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"What happened here?!\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"DistanceTravelledStage\": [\n" + 
                "        40\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"Can I help? Can anyone tell me how I can help?\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"DistanceTravelledStage\": [\n" + 
                "        40\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"What is going on?\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"DistanceTravelledStage\": [\n" + 
                "        40\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"Where is Officer Reynolds?\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"DistanceTravelledStage\": [\n" + 
                "        40\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"Has anyone seen Officer Reynolds?!\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"visibility\": {\n" + 
                "        \"Officer_Reynolds\": true\n" + 
                "    },\n" + 
                "    \"ClickEntityStage\": [\n" + 
                "        \"Officer_Reynolds\"\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"&9Officer Reynolds\": \"Get back! The flames will burn you to a crisp!\",\n" + 
                "            \"[PLAYER]\": \"What happened here!?\",\n" + 
                "            \"&9Officer Reynolds\": \"Massive fire. Spreading fast!\",\n" + 
                "            \"&9Officer Reynolds\": \"Started at Wald's house. Biggest we've seen!\",\n" + 
                "            \"&9Officer Reynolds\": \"Flames? Unstoppable! Spreading? Unavoidable!\",\n" + 
                "            \"&9Officer Reynolds\": \"Might not be able to stop this one.\",\n" + 
                "            \"&9Officer Reynolds\": \"Leave or help!\",\n" + 
                "            \"[PLAYER]\": \"How can I help?\",\n" + 
                "            \"&9Officer Reynolds\": \"There's been rumors of similar fires across the region.\",\n" + 
                "            \"&9Officer Reynolds\": \"Finding out who's responsible may be how we stop this!\",\n" + 
                "            \"&9Officer Reynolds\": \"Start with the source. Wald's house!\",\n" + 
                "            \"&9Officer Reynolds\": \"Wait, help us rescuing pokemon trapped by the fire first please!\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"visibility\": {\n" + 
                "        \"Wald_One\": true,\n" + 
                "        \"Pokemon_Ranger\": false\n" + 
                "    },\n" + 
                "    \"TimedStage\": [\n" + 
                "        0.1\n" + 
                "    ],\n" + 
                "    \"ObjectiveStage\": [\n" + 
                "        \"Rescue all the pokemon on the ground trapped by the fire\",\n" + 
                "        25\n" + 
                "    ],\n" + 
                "    \"ShowPokemonStatueStage\": [\n" + 
                "        \"Kricketot\",\n" + 
                "        357.5,\n" + 
                "        43.0,\n" + 
                "        721.5,\n" + 
                "        180\n" + 
                "    ],\n" + 
                "    \"RegionEntryStage\": [\n" + 
                "        362,\n" + 
                "        43,\n" + 
                "        726,\n" + 
                "        352,\n" + 
                "        53,\n" + 
                "        716\n" + 
                "    ],\n" + 
                "    \"visibility\": {\n" + 
                "        \"Officer_Reynolds\": false\n" + 
                "    },\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"A trapped Pokémon! I need to save them!\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"UsePokemonTypeStage\": [\n" + 
                "        362,\n" + 
                "        43,\n" + 
                "        726,\n" + 
                "        352,\n" + 
                "        53,\n" + 
                "        716,\n" + 
                "        \"Water\",\n" + 
                "        \"&cA fire?\",\n" + 
                "        \"&7A &bWater &7pokemon should be able to put it out!\"\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        358,\n" + 
                "        43,\n" + 
                "        720,\n" + 
                "        356,\n" + 
                "        43,\n" + 
                "        722,\n" + 
                "        -33\n" + 
                "    ],\n" + 
                "    \"SendMessageStage\": [\n" + 
                "        \"&eTrapped Pokemon rescued: 1/4\"\n" + 
                "    ],\n" + 
                "    \"HidePokemonStatueStage\": [\n" + 
                "        357.5,\n" + 
                "        43.0,\n" + 
                "        721.5,\n" + 
                "        180\n" + 
                "    ],\n" + 
                "    \"ShowPokemonStatueStage\": [\n" + 
                "        \"Budew\",\n" + 
                "        295.5,\n" + 
                "        48.0,\n" + 
                "        728.5,\n" + 
                "        115\n" + 
                "    ],\n" + 
                "    \"RegionEntryStage\": [\n" + 
                "        300,\n" + 
                "        45,\n" + 
                "        733,\n" + 
                "        290,\n" + 
                "        55,\n" + 
                "        723\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"It's okay, bud! I'll save you!\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"UsePokemonTypeStage\": [\n" + 
                "        300,\n" + 
                "        45,\n" + 
                "        733,\n" + 
                "        290,\n" + 
                "        55,\n" + 
                "        723,\n" + 
                "        \"Water\",\n" + 
                "        \"&cA fire?\",\n" + 
                "        \"&7A &bWater &7pokemon should be able to put it out!\"\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        296,\n" + 
                "        48,\n" + 
                "        729,\n" + 
                "        294,\n" + 
                "        48,\n" + 
                "        727,\n" + 
                "        -38\n" + 
                "    ],\n" + 
                "    \"SendMessageStage\": [\n" + 
                "        \"&eTrapped Pokemon rescued: 2/4\"\n" + 
                "    ],\n" + 
                "    \"HidePokemonStatueStage\": [\n" + 
                "        295.5,\n" + 
                "        48.0,\n" + 
                "        728.5,\n" + 
                "        115\n" + 
                "    ],\n" + 
                "    \"ShowPokemonStatueStage\": [\n" + 
                "        \"Cherubi\",\n" + 
                "        271.5,\n" + 
                "        44.0,\n" + 
                "        735.5,\n" + 
                "        0\n" + 
                "    ],\n" + 
                "    \"RegionEntryStage\": [\n" + 
                "        276,\n" + 
                "        44,\n" + 
                "        740,\n" + 
                "        266,\n" + 
                "        54,\n" + 
                "        730\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"Don't move, I'll get rid of these flames.\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"UsePokemonTypeStage\": [\n" + 
                "        276,\n" + 
                "        44,\n" + 
                "        740,\n" + 
                "        266,\n" + 
                "        54,\n" + 
                "        730,\n" + 
                "        \"Water\",\n" + 
                "        \"&cA fire?\",\n" + 
                "        \"&7A &bWater &7pokemon should be able to put it out!\"\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        270,\n" + 
                "        44,\n" + 
                "        734,\n" + 
                "        272,\n" + 
                "        44,\n" + 
                "        736,\n" + 
                "        -34\n" + 
                "    ],\n" + 
                "    \"SendMessageStage\": [\n" + 
                "        \"&eTrapped Pokemon rescued: 3/4\"\n" + 
                "    ],\n" + 
                "    \"HidePokemonStatueStage\": [\n" + 
                "        271.5,\n" + 
                "        44.0,\n" + 
                "        735.5,\n" + 
                "        0\n" + 
                "    ],\n" + 
                "    \"ShowPokemonStatueStage\": [\n" + 
                "        \"Seedot\",\n" + 
                "        361.5,\n" + 
                "        40.0,\n" + 
                "        768.5,\n" + 
                "        180\n" + 
                "    ],\n" + 
                "    \"RegionEntryStage\": [\n" + 
                "        366,\n" + 
                "        40,\n" + 
                "        773,\n" + 
                "        356,\n" + 
                "        50,\n" + 
                "        763\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"I'll get you to safety, don't worry!\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"UsePokemonTypeStage\": [\n" + 
                "        366,\n" + 
                "        40,\n" + 
                "        773,\n" + 
                "        356,\n" + 
                "        50,\n" + 
                "        763,\n" + 
                "        \"Water\",\n" + 
                "        \"&cA fire?\",\n" + 
                "        \"&7A &bWater &7pokemon should be able to put it out!\"\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        362,\n" + 
                "        40,\n" + 
                "        769,\n" + 
                "        360,\n" + 
                "        40,\n" + 
                "        767,\n" + 
                "        -30\n" + 
                "    ],\n" + 
                "    \"SendMessageStage\": [\n" + 
                "        \"&eTrapped Pokemon rescued: 4/4\"\n" + 
                "    ],\n" + 
                "    \"HidePokemonStatueStage\": [\n" + 
                "        361.5,\n" + 
                "        40.0,\n" + 
                "        768.5,\n" + 
                "        180\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        2.0\n" + 
                "    ],\n" + 
                "    \"RegionEntryStage\": [\n" + 
                "        315,\n" + 
                "        63,\n" + 
                "        760,\n" + 
                "        273,\n" + 
                "        83,\n" + 
                "        779\n" + 
                "    ],\n" + 
                "    \"ObjectiveStage\": [\n" + 
                "        \"Make your way to Wald's house\"\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"This must be Wald's house.\",\n" + 
                "            \"[PLAYER]\": \"Need to put out the flames with Water Pokémon.\",\n" + 
                "            \"[PLAYER]\": \"There's no other way to the door!\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        2.0\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"Wald\": \"Hey, you! Save me, please!\",\n" + 
                "            \"Wald\": \"Hope you didn't waste all your bravery on those Pokémon!\",\n" + 
                "            \"Wald\": \"Help a fellow trainer out!\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"UsePokemonTypeStage\": [\n" + 
                "        315,\n" + 
                "        63,\n" + 
                "        760,\n" + 
                "        273,\n" + 
                "        83,\n" + 
                "        779,\n" + 
                "        \"Water\",\n" + 
                "        \"&cA fire?\",\n" + 
                "        \"&7A &bWater &7pokemon should be able to put it out!\"\n" + 
                "    ],\n" + 
                "    \"ObjectiveStage\": [\n" + 
                "        \"Use a Water pokemon to rescue Wald\"\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        309,\n" + 
                "        66,\n" + 
                "        771,\n" + 
                "        312,\n" + 
                "        66,\n" + 
                "        771,\n" + 
                "        -56\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        309,\n" + 
                "        67,\n" + 
                "        771,\n" + 
                "        312,\n" + 
                "        67,\n" + 
                "        771,\n" + 
                "        -57\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        309,\n" + 
                "        68,\n" + 
                "        771,\n" + 
                "        312,\n" + 
                "        68,\n" + 
                "        771,\n" + 
                "        -58\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        309,\n" + 
                "        69,\n" + 
                "        771,\n" + 
                "        312,\n" + 
                "        69,\n" + 
                "        771,\n" + 
                "        -59\n" + 
                "    ],\n" + 
                "    \"ClickEntityStage\": [\n" + 
                "        \"Wald_One\"\n" + 
                "    ],\n" + 
                "    \"ObjectiveStage\": [\n" + 
                "        \"Go to Wald\"\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"Wald\": \"Hey, thanks! See ya!\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"NpcMoveStage\": [\n" + 
                "        \"Wald_One\",\n" + 
                "        304.0,\n" + 
                "        70.0,\n" + 
                "        771.0\n" + 
                "    ],\n" + 
                "    \"NpcMoveStage\": [\n" + 
                "        \"Wald_One\",\n" + 
                "        305.0,\n" + 
                "        69.5,\n" + 
                "        773.0\n" + 
                "    ],\n" + 
                "    \"NpcMoveStage\": [\n" + 
                "        \"Wald_One\",\n" + 
                "        308.0,\n" + 
                "        68.5,\n" + 
                "        775.5\n" + 
                "    ],\n" + 
                "    \"NpcMoveStage\": [\n" + 
                "        \"Wald_One\",\n" + 
                "        309.5,\n" + 
                "        67.0,\n" + 
                "        775.5\n" + 
                "    ],\n" + 
                "    \"PlaySoundStage\": [\n" + 
                "        \"entity_zombie_break_door_wood\",\n" + 
                "        1.8\n" + 
                "    ],\n" + 
                "    \"SendMessageStage\": [\n" + 
                "        \"A branch broke off and hit you!\"\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        0.5\n" + 
                "    ],\n" + 
                "    \"PotionStage\": [\n" + 
                "        \"blindness\",\n" + 
                "        130\n" + 
                "    ],\n" + 
                "    \"TeleportStage\": [\n" + 
                "        300.5,\n" + 
                "        70.0,\n" + 
                "        766.5,\n" + 
                "        90\n" + 
                "    ],\n" + 
                "    \"FreezeStage\": [\n" + 
                "        \n" + 
                "    ],\n" + 
                "    \"visibility\": {\n" + 
                "        \"Wald_One\": false,\n" + 
                "        \"Wald_Two\": true\n" + 
                "    },\n" + 
                "    \"TimedStage\": [\n" + 
                "        2.0\n" + 
                "    ],\n" + 
                "    \"ShowPokemonStatueStage\": [\n" + 
                "        \"Azelf\",\n" + 
                "        296.5,\n" + 
                "        70.0625,\n" + 
                "        766.5,\n" + 
                "        270\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        4.0\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"&9Azelf\": \"If there's one thing true of fire…\",\n" + 
                "            \"&9Azelf\": \"…it's that it cannot survive within the lifeblood of our world.\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        2.0\n" + 
                "    ],\n" + 
                "    \"HidePokemonStatueStage\": [\n" + 
                "        296.5,\n" + 
                "        70.0625,\n" + 
                "        766.5,\n" + 
                "        270\n" + 
                "    ],\n" + 
                "    \"TeleportStage\": [\n" + 
                "        300.5,\n" + 
                "        70.0,\n" + 
                "        766.5,\n" + 
                "        235\n" + 
                "    ],\n" + 
                "    \"ShowPokemonStatueStage\": [\n" + 
                "        \"Azelf\",\n" + 
                "        305.5,\n" + 
                "        70.0,\n" + 
                "        762.5,\n" + 
                "        55\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"&9Azelf\": \"Every fearsome flame can be drowned.\",\n" + 
                "            \"&9Azelf\": \"It's destruction stopped.\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        2.0\n" + 
                "    ],\n" + 
                "    \"HidePokemonStatueStage\": [\n" + 
                "        305.5,\n" + 
                "        70.0,\n" + 
                "        762.5,\n" + 
                "        55\n" + 
                "    ],\n" + 
                "    \"TeleportStage\": [\n" + 
                "        300.5,\n" + 
                "        70.0,\n" + 
                "        766.5,\n" + 
                "        330\n" + 
                "    ],\n" + 
                "    \"ShowPokemonStatueStage\": [\n" + 
                "        \"Azelf\",\n" + 
                "        303.5,\n" + 
                "        70.0,\n" + 
                "        770.5,\n" + 
                "        140\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"&9Azelf\": \"It's wrongs ready to be undone.\",\n" + 
                "            \"&9Azelf\": \"The water will be summoned, my good Trainer.\",\n" + 
                "            \"&9Azelf\": \"I will summon the rain to end this chaos.\",\n" + 
                "            \"&9Azelf\": \"Our world is counting on you.\",\n" + 
                "            \"&9Azelf\": \"Today, and deep into the tomorrow.\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        2.0\n" + 
                "    ],\n" + 
                "    \"PlaySoundStage\": [\n" + 
                "        \"weather_rain\",\n" + 
                "        0.1\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        389,\n" + 
                "        50,\n" + 
                "        776,\n" + 
                "        389,\n" + 
                "        50,\n" + 
                "        776,\n" + 
                "        -41\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        273,\n" + 
                "        74,\n" + 
                "        726,\n" + 
                "        273,\n" + 
                "        74,\n" + 
                "        736,\n" + 
                "        -64\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        273,\n" + 
                "        87,\n" + 
                "        713,\n" + 
                "        273,\n" + 
                "        87,\n" + 
                "        713,\n" + 
                "        -77\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        272,\n" + 
                "        95,\n" + 
                "        712,\n" + 
                "        272,\n" + 
                "        95,\n" + 
                "        712,\n" + 
                "        -85\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        254,\n" + 
                "        77,\n" + 
                "        722,\n" + 
                "        254,\n" + 
                "        77,\n" + 
                "        729,\n" + 
                "        -67\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        254,\n" + 
                "        90,\n" + 
                "        745,\n" + 
                "        254,\n" + 
                "        90,\n" + 
                "        745,\n" + 
                "        -80\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        261,\n" + 
                "        77,\n" + 
                "        746,\n" + 
                "        264,\n" + 
                "        77,\n" + 
                "        746,\n" + 
                "        -67\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        276,\n" + 
                "        77,\n" + 
                "        747,\n" + 
                "        276,\n" + 
                "        77,\n" + 
                "        747,\n" + 
                "        -67\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        275,\n" + 
                "        76,\n" + 
                "        716,\n" + 
                "        275,\n" + 
                "        76,\n" + 
                "        716,\n" + 
                "        -66\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        0.5\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        320,\n" + 
                "        64,\n" + 
                "        792,\n" + 
                "        322,\n" + 
                "        64,\n" + 
                "        792,\n" + 
                "        -54\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        364,\n" + 
                "        67,\n" + 
                "        791,\n" + 
                "        364,\n" + 
                "        67,\n" + 
                "        790,\n" + 
                "        -57\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        309,\n" + 
                "        66,\n" + 
                "        730,\n" + 
                "        311,\n" + 
                "        66,\n" + 
                "        730,\n" + 
                "        -56\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        356,\n" + 
                "        52,\n" + 
                "        695,\n" + 
                "        348,\n" + 
                "        52,\n" + 
                "        695,\n" + 
                "        -42\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        389,\n" + 
                "        51,\n" + 
                "        751,\n" + 
                "        389,\n" + 
                "        51,\n" + 
                "        758,\n" + 
                "        -41\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        315,\n" + 
                "        85,\n" + 
                "        732,\n" + 
                "        313,\n" + 
                "        85,\n" + 
                "        735,\n" + 
                "        -75\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        0.5\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        286,\n" + 
                "        85,\n" + 
                "        708,\n" + 
                "        286,\n" + 
                "        85,\n" + 
                "        708,\n" + 
                "        -75\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        324,\n" + 
                "        113,\n" + 
                "        705,\n" + 
                "        323,\n" + 
                "        113,\n" + 
                "        705,\n" + 
                "        -103\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        293,\n" + 
                "        104,\n" + 
                "        714,\n" + 
                "        295,\n" + 
                "        104,\n" + 
                "        711,\n" + 
                "        -94\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        331,\n" + 
                "        98,\n" + 
                "        715,\n" + 
                "        328,\n" + 
                "        98,\n" + 
                "        714,\n" + 
                "        -88\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        350,\n" + 
                "        83,\n" + 
                "        708,\n" + 
                "        352,\n" + 
                "        83,\n" + 
                "        706,\n" + 
                "        -73\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        279,\n" + 
                "        65,\n" + 
                "        704,\n" + 
                "        283,\n" + 
                "        65,\n" + 
                "        704,\n" + 
                "        55\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        303,\n" + 
                "        58,\n" + 
                "        730,\n" + 
                "        302,\n" + 
                "        58,\n" + 
                "        733,\n" + 
                "        -48\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        335,\n" + 
                "        72,\n" + 
                "        663,\n" + 
                "        335,\n" + 
                "        72,\n" + 
                "        663,\n" + 
                "        -62\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        366,\n" + 
                "        52,\n" + 
                "        664,\n" + 
                "        370,\n" + 
                "        52,\n" + 
                "        668,\n" + 
                "        -42\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        1.0\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        388,\n" + 
                "        85,\n" + 
                "        740,\n" + 
                "        388,\n" + 
                "        85,\n" + 
                "        740,\n" + 
                "        -75\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        391,\n" + 
                "        89,\n" + 
                "        741,\n" + 
                "        391,\n" + 
                "        89,\n" + 
                "        741,\n" + 
                "        -79\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        388,\n" + 
                "        85,\n" + 
                "        744,\n" + 
                "        388,\n" + 
                "        85,\n" + 
                "        744,\n" + 
                "        -75\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        387,\n" + 
                "        81,\n" + 
                "        743,\n" + 
                "        387,\n" + 
                "        81,\n" + 
                "        743,\n" + 
                "        -71\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        318,\n" + 
                "        73,\n" + 
                "        697,\n" + 
                "        318,\n" + 
                "        73,\n" + 
                "        697,\n" + 
                "        -63\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        315,\n" + 
                "        86,\n" + 
                "        694,\n" + 
                "        315,\n" + 
                "        86,\n" + 
                "        694,\n" + 
                "        -76\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        397,\n" + 
                "        59,\n" + 
                "        760,\n" + 
                "        397,\n" + 
                "        59,\n" + 
                "        760,\n" + 
                "        -49\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        311,\n" + 
                "        78,\n" + 
                "        762,\n" + 
                "        311,\n" + 
                "        78,\n" + 
                "        766,\n" + 
                "        -68\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        1.0\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        309,\n" + 
                "        69,\n" + 
                "        772,\n" + 
                "        309,\n" + 
                "        69,\n" + 
                "        772,\n" + 
                "        -59\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        310,\n" + 
                "        67,\n" + 
                "        778,\n" + 
                "        306,\n" + 
                "        67,\n" + 
                "        778,\n" + 
                "        -57\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        298,\n" + 
                "        85,\n" + 
                "        768,\n" + 
                "        298,\n" + 
                "        85,\n" + 
                "        768,\n" + 
                "        -75\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        326,\n" + 
                "        98,\n" + 
                "        764,\n" + 
                "        326,\n" + 
                "        98,\n" + 
                "        765,\n" + 
                "        -88\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        307,\n" + 
                "        106,\n" + 
                "        780,\n" + 
                "        307,\n" + 
                "        106,\n" + 
                "        780,\n" + 
                "        -96\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        337,\n" + 
                "        99,\n" + 
                "        767,\n" + 
                "        337,\n" + 
                "        99,\n" + 
                "        767,\n" + 
                "        -89\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        327,\n" + 
                "        47,\n" + 
                "        715,\n" + 
                "        325,\n" + 
                "        47,\n" + 
                "        715,\n" + 
                "        -37\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        314,\n" + 
                "        60,\n" + 
                "        735,\n" + 
                "        314,\n" + 
                "        60,\n" + 
                "        735,\n" + 
                "        -50\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        277,\n" + 
                "        58,\n" + 
                "        744,\n" + 
                "        277,\n" + 
                "        58,\n" + 
                "        744,\n" + 
                "        -48\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        340,\n" + 
                "        85,\n" + 
                "        724,\n" + 
                "        339,\n" + 
                "        85,\n" + 
                "        724,\n" + 
                "        -75\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        1.0\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        355,\n" + 
                "        69,\n" + 
                "        736,\n" + 
                "        355,\n" + 
                "        69,\n" + 
                "        736,\n" + 
                "        -59\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        328,\n" + 
                "        58,\n" + 
                "        691,\n" + 
                "        328,\n" + 
                "        58,\n" + 
                "        691,\n" + 
                "        -45\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        362,\n" + 
                "        69,\n" + 
                "        783,\n" + 
                "        362,\n" + 
                "        69,\n" + 
                "        783,\n" + 
                "        -59\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        317,\n" + 
                "        85,\n" + 
                "        757,\n" + 
                "        317,\n" + 
                "        85,\n" + 
                "        757,\n" + 
                "        -75\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        320,\n" + 
                "        89,\n" + 
                "        745,\n" + 
                "        320,\n" + 
                "        89,\n" + 
                "        745,\n" + 
                "        -79\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        301,\n" + 
                "        93,\n" + 
                "        736,\n" + 
                "        301,\n" + 
                "        93,\n" + 
                "        736,\n" + 
                "        -83\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        311,\n" + 
                "        105,\n" + 
                "        737,\n" + 
                "        311,\n" + 
                "        105,\n" + 
                "        737,\n" + 
                "        -95\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        322,\n" + 
                "        111,\n" + 
                "        728,\n" + 
                "        322,\n" + 
                "        111,\n" + 
                "        728,\n" + 
                "        -101\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        326,\n" + 
                "        115,\n" + 
                "        737,\n" + 
                "        326,\n" + 
                "        115,\n" + 
                "        737,\n" + 
                "        -105\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        312,\n" + 
                "        124,\n" + 
                "        746,\n" + 
                "        312,\n" + 
                "        124,\n" + 
                "        746,\n" + 
                "        -114\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        1.0\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        300,\n" + 
                "        120,\n" + 
                "        729,\n" + 
                "        300,\n" + 
                "        120,\n" + 
                "        729,\n" + 
                "        -110\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        321,\n" + 
                "        92,\n" + 
                "        718,\n" + 
                "        321,\n" + 
                "        92,\n" + 
                "        718,\n" + 
                "        -82\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        296,\n" + 
                "        107,\n" + 
                "        758,\n" + 
                "        296,\n" + 
                "        107,\n" + 
                "        758,\n" + 
                "        -97\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        300,\n" + 
                "        95,\n" + 
                "        728,\n" + 
                "        300,\n" + 
                "        95,\n" + 
                "        728,\n" + 
                "        -85\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        279,\n" + 
                "        98,\n" + 
                "        693,\n" + 
                "        279,\n" + 
                "        98,\n" + 
                "        693,\n" + 
                "        -88\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        305,\n" + 
                "        98,\n" + 
                "        743,\n" + 
                "        305,\n" + 
                "        98,\n" + 
                "        743,\n" + 
                "        -88\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        1.0\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        309,\n" + 
                "        74,\n" + 
                "        721,\n" + 
                "        309,\n" + 
                "        74,\n" + 
                "        721,\n" + 
                "        -64\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        317,\n" + 
                "        64,\n" + 
                "        723,\n" + 
                "        317,\n" + 
                "        64,\n" + 
                "        723,\n" + 
                "        -54\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        313,\n" + 
                "        76,\n" + 
                "        722,\n" + 
                "        313,\n" + 
                "        76,\n" + 
                "        722,\n" + 
                "        -66\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        313,\n" + 
                "        77,\n" + 
                "        734,\n" + 
                "        313,\n" + 
                "        77,\n" + 
                "        734,\n" + 
                "        -67\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        284,\n" + 
                "        102,\n" + 
                "        688,\n" + 
                "        284,\n" + 
                "        102,\n" + 
                "        688,\n" + 
                "        -92\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        355,\n" + 
                "        61,\n" + 
                "        747,\n" + 
                "        355,\n" + 
                "        61,\n" + 
                "        747,\n" + 
                "        -51\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        319,\n" + 
                "        59,\n" + 
                "        758,\n" + 
                "        319,\n" + 
                "        59,\n" + 
                "        758,\n" + 
                "        -49\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        322,\n" + 
                "        50,\n" + 
                "        767,\n" + 
                "        322,\n" + 
                "        50,\n" + 
                "        767,\n" + 
                "        -40\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        307,\n" + 
                "        52,\n" + 
                "        741,\n" + 
                "        307,\n" + 
                "        52,\n" + 
                "        741,\n" + 
                "        -42\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        275,\n" + 
                "        48,\n" + 
                "        714,\n" + 
                "        275,\n" + 
                "        48,\n" + 
                "        714,\n" + 
                "        -38\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        1.0\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        309,\n" + 
                "        47,\n" + 
                "        704,\n" + 
                "        309,\n" + 
                "        47,\n" + 
                "        704,\n" + 
                "        -37\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        346,\n" + 
                "        46,\n" + 
                "        757,\n" + 
                "        346,\n" + 
                "        46,\n" + 
                "        757,\n" + 
                "        -36\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        361,\n" + 
                "        44,\n" + 
                "        794,\n" + 
                "        361,\n" + 
                "        44,\n" + 
                "        794,\n" + 
                "        -34\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        276,\n" + 
                "        46,\n" + 
                "        731,\n" + 
                "        276,\n" + 
                "        46,\n" + 
                "        731,\n" + 
                "        -36\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        289,\n" + 
                "        44,\n" + 
                "        700,\n" + 
                "        289,\n" + 
                "        44,\n" + 
                "        700,\n" + 
                "        -34\n" + 
                "    ],\n" + 
                "    \"ProgressiveWallStage\": [\n" + 
                "        340,\n" + 
                "        45,\n" + 
                "        742,\n" + 
                "        340,\n" + 
                "        45,\n" + 
                "        742,\n" + 
                "        -35\n" + 
                "    ],\n" + 
                "    \"UnfreezeStage\": [\n" + 
                "        \n" + 
                "    ],\n" + 
                "    \"HidePokemonStatueStage\": [\n" + 
                "        303.5,\n" + 
                "        70.0,\n" + 
                "        770.5,\n" + 
                "        140\n" + 
                "    ],\n" + 
                "    \"ClickEntityStage\": [\n" + 
                "        \"Wald_Two\"\n" + 
                "    ],\n" + 
                "    \"ObjectiveStage\": [\n" + 
                "        \"Go outside and meet Wald\"\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"Wald\": \"Whoa! You made it out alive!\",\n" + 
                "            \"Wald\": \"Thanks again, by the way. I'm still shaking.\",\n" + 
                "            \"[PLAYER]\": \"What happened?\",\n" + 
                "            \"Wald\": \"You mean what started the fire? I don't want to say.\",\n" + 
                "            \"[PLAYER]\": \"Why's that?\",\n" + 
                "            \"Wald\": \"Sounds suspicious, doesn't it.\",\n" + 
                "            \"Wald\": \"But I don't really care.\",\n" + 
                "            \"Wald\": \"Just leave me alone.\",\n" + 
                "            \"[PLAYER]\": \"Did you start this fire?\",\n" + 
                "            \"[PLAYER]\": \"It nearly burned the town down.\",\n" + 
                "            \"[PLAYER]\": \"If Azelf hadn't appeared and-\",\n" + 
                "            \"Wald\": \"Azelf? Appeared? Is my house haunted or something??\",\n" + 
                "            \"[PLAYER]\": \"What do you mean?\",\n" + 
                "            \"Wald\": \"Forget it. I'm getting out of here.\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"AutoNpcBattleStage\": [\n" + 
                "        \"Wald_Two\",\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"Battle me. If I win, you tell me everything.\",\n" + 
                "            \"Wald\": \"Fine. But if I win, everyone has to leave me alone.\"\n" + 
                "        },\n" + 
                "        {\n" + 
                "            \n" + 
                "        },\n" + 
                "        {\n" + 
                "            \"Wald\": \"Now Leave me alone!\"\n" + 
                "        },\n" + 
                "        [\n" + 
                "            {\n" + 
                "                \"species\": \"Carnivine\",\n" + 
                "                \"level\": 12,\n" + 
                "                \"moves\": [\n" + 
                "                    \"Vine Whip\",\n" + 
                "                    \"Bullet Seed\",\n" + 
                "                    \"Bug Bite\",\n" + 
                "                    \"Synthesis\"\n" + 
                "                ],\n" + 
                "                \"gender\": \"Female\",\n" + 
                "                \"ability\": \"Levitate\",\n" + 
                "                \"nature\": \"Adamant\",\n" + 
                "                \"item\": \"LEFTOVERS\",\n" + 
                "                \"evs\": {\n" + 
                "                    \"attack\": 150\n" + 
                "                }\n" + 
                "            },\n" + 
                "            {\n" + 
                "                \"species\": \"Combee\",\n" + 
                "                \"level\": 14,\n" + 
                "                \"moves\": [\n" + 
                "                    \"Gust\",\n" + 
                "                    \"Bug Bite\"\n" + 
                "                ],\n" + 
                "                \"gender\": \"Male\",\n" + 
                "                \"ability\": \"Hustle\",\n" + 
                "                \"nature\": \"Jolly\",\n" + 
                "                \"evs\": {\n" + 
                "                    \"speed\": 50,\n" + 
                "                    \"attack\": 50,\n" + 
                "                    \"defense\": 50,\n" + 
                "                    \"special_defense\": 50,\n" + 
                "                    \"hp\": 50\n" + 
                "                }\n" + 
                "            },\n" + 
                "            {\n" + 
                "                \"species\": \"Kricketune\",\n" + 
                "                \"level\": 13,\n" + 
                "                \"moves\": [\n" + 
                "                    \"Growl\",\n" + 
                "                    \"Bide\",\n" + 
                "                    \"Leech Life\",\n" + 
                "                    \"Fury Cutter\"\n" + 
                "                ],\n" + 
                "                \"gender\": \"Female\",\n" + 
                "                \"ability\": \"Swarm\",\n" + 
                "                \"nature\": \"Adamant\"\n" + 
                "            }\n" + 
                "        ]\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"Wald\": \"Okay, all right!\",\n" + 
                "            \"Wald\": \"First my place burns, then I'm beaten in battle?\",\n" + 
                "            \"[PLAYER]\": \"What started the fire? We had a deal.\",\n" + 
                "            \"Wald\": \"You think I was afraid to tell you for no reason?\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"TeleportStage\": [\n" + 
                "        323.5,\n" + 
                "        72.0,\n" + 
                "        766.5,\n" + 
                "        200\n" + 
                "    ],\n" + 
                "    \"HidePlayerStage\": [\n" + 
                "        \n" + 
                "    ],\n" + 
                "    \"visibility\": {\n" + 
                "        \"Wald_Two\": false\n" + 
                "    },\n" + 
                "    \"FreezeStage\": [\n" + 
                "        \n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"Wald\": \"First, I'm woken up in the middle of the night, right?\",\n" + 
                "            \"Wald\": \"Something smashed through my door.\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"PlaySoundStage\": [\n" + 
                "        \"entity_zombie_break_door_wood\",\n" + 
                "        0.65\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        1.5\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"Wald\": \"Something that shook the house with every footstep.\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"PlaySoundStage\": [\n" + 
                "        \"entity_zombie_attack_door_wood\",\n" + 
                "        0.1\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        1.5\n" + 
                "    ],\n" + 
                "    \"PlaySoundStage\": [\n" + 
                "        \"entity_zombie_attack_door_wood\",\n" + 
                "        0.1\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        1.5\n" + 
                "    ],\n" + 
                "    \"PlaySoundStage\": [\n" + 
                "        \"entity_zombie_attack_door_wood\",\n" + 
                "        0.1\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        1.5\n" + 
                "    ],\n" + 
                "    \"PlaySoundStage\": [\n" + 
                "        \"entity_zombie_attack_door_wood\",\n" + 
                "        0.1\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        1.5\n" + 
                "    ],\n" + 
                "    \"PlaySoundStage\": [\n" + 
                "        \"entity_zombie_attack_door_wood\",\n" + 
                "        0.1\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        1.5\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"Wald\": \"I was too frightened to move.\",\n" + 
                "            \"Wald\": \"I just stayed in bed, frozen in place.\",\n" + 
                "            \"Wald\": \"And I could barely lift my head up to see…\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"ShowPokemonStatueStage\": [\n" + 
                "        \"Groudon\",\n" + 
                "        341.5,\n" + 
                "        43.0,\n" + 
                "        729.5,\n" + 
                "        21\n" + 
                "    ],\n" + 
                "    \"TimedStage\": [\n" + 
                "        2.0\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"Wald\": \"…but I could have sworn I saw a Groudon.\",\n" + 
                "            \"Wald\": \"But what would a legendary Pokémon be doing in my hometown?\",\n" + 
                "            \"Wald\": \"I must have been dreaming, right?\",\n" + 
                "            \"Wald\": \"Except then BOOM. Flames. Fire. Scorched earth.\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"PlaySoundStage\": [\n" + 
                "        \"entity_lightning_impact\",\n" + 
                "        0.2\n" + 
                "    ],\n" + 
                "    \"HidePokemonStatueStage\": [\n" + 
                "        341.5,\n" + 
                "        43.0,\n" + 
                "        729.5,\n" + 
                "        21\n" + 
                "    ],\n" + 
                "    \"ParticleStage\": [\n" + 
                "        \"explosion_large\",\n" + 
                "        381.5,\n" + 
                "        65.5,\n" + 
                "        742.5,\n" + 
                "        5\n" + 
                "    ],\n" + 
                "    \"ParticleStage\": [\n" + 
                "        \"explosion_large\",\n" + 
                "        347.5,\n" + 
                "        43.0,\n" + 
                "        743.5,\n" + 
                "        5\n" + 
                "    ],\n" + 
                "    \"ParticleStage\": [\n" + 
                "        \"explosion_large\",\n" + 
                "        325.5,\n" + 
                "        54.5,\n" + 
                "        732.5,\n" + 
                "        5\n" + 
                "    ],\n" + 
                "    \"ParticleStage\": [\n" + 
                "        \"explosion_large\",\n" + 
                "        310.5,\n" + 
                "        68.5,\n" + 
                "        772.5,\n" + 
                "        5\n" + 
                "    ],\n" + 
                "    \"ParticleStage\": [\n" + 
                "        \"explosion_large\",\n" + 
                "        362.5,\n" + 
                "        67.5,\n" + 
                "        789.5,\n" + 
                "        5\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"Wald\": \"I would have thought I was crazy.\",\n" + 
                "            \"Wald\": \"I still think I might be.\",\n" + 
                "            \"Wald\": \"But if you really did see Azelf in there…I don't know.\",\n" + 
                "            \"Wald\": \"Feels like something big is going on.\",\n" + 
                "            \"Wald\": \"And I can't wait to not be a part of it anymore.\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"visibility\": {\n" + 
                "        \"Wald_Two\": true\n" + 
                "    },\n" + 
                "    \"UnfreezeStage\": [\n" + 
                "        \n" + 
                "    ],\n" + 
                "    \"TeleportStage\": [\n" + 
                "        338.5,\n" + 
                "        43.0,\n" + 
                "        729.5,\n" + 
                "        180\n" + 
                "    ],\n" + 
                "    \"ShowPlayerStage\": [\n" + 
                "        \n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"Wald\": \"Now if you don't mind, I'm going to go take a three-day nap.\",\n" + 
                "            \"[PLAYER]\": \"I have to prepare to take on the gym leader anyways. Thanks for the information.\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"visibility\": {\n" + 
                "        \"Wald_Two\": false\n" + 
                "    },\n" + 
                "    \"GiveCoinsStage\": [\n" + 
                "        10000\n" + 
                "    ],\n" + 
                "    \"GiveTokensStage\": [\n" + 
                "        15\n" + 
                "    ],\n" + 
                "    \"GiveTrainerExperienceStage\": [\n" + 
                "        7000\n" + 
                "    ],\n" + 
                "    \"GiveItemStage\": [\n" + 
                "        \"PP_Up\",\n" + 
                "        3\n" + 
                "    ],\n" + 
                "    \"GlobalRegionEntryStage\": [\n" + 
                "        306,\n" + 
                "        60,\n" + 
                "        722,\n" + 
                "        299,\n" + 
                "        70,\n" + 
                "        732\n" + 
                "    ],\n" + 
                "    \"NoExperienceWildPokemonBattleStage\": [\n" + 
                "        304.5,\n" + 
                "        61.0,\n" + 
                "        730.5,\n" + 
                "        165,\n" + 
                "        [\n" + 
                "            {\n" + 
                "                \"species\": \"Scyther\",\n" + 
                "                \"level\": 10,\n" + 
                "                \"moves\": [\n" + 
                "                    \"Aerial Ace\",\n" + 
                "                    \"X-Scissor\",\n" + 
                "                    \"Brick Break\",\n" + 
                "                    \"Roost\"\n" + 
                "                ],\n" + 
                "                \"gender\": \"Male\",\n" + 
                "                \"ability\": \"Technician\",\n" + 
                "                \"nature\": \"Adamant\",\n" + 
                "                \"item\": \"EVIOLITE\",\n" + 
                "                \"evs\": {\n" + 
                "                    \"speed\": 50,\n" + 
                "                    \"attack\": 50\n" + 
                "                }\n" + 
                "            }\n" + 
                "        ]\n" + 
                "    ],\n" + 
                "    \"GlobalRegionEntryStage\": [\n" + 
                "        306,\n" + 
                "        60,\n" + 
                "        722,\n" + 
                "        299,\n" + 
                "        70,\n" + 
                "        732\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"Wild Pokémon are lashing out!\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"GlobalRegionEntryStage\": [\n" + 
                "        314,\n" + 
                "        46,\n" + 
                "        756,\n" + 
                "        326,\n" + 
                "        53,\n" + 
                "        763\n" + 
                "    ],\n" + 
                "    \"NoExperienceWildPokemonBattleStage\": [\n" + 
                "        317.5,\n" + 
                "        49.5,\n" + 
                "        758.5,\n" + 
                "        90,\n" + 
                "        [\n" + 
                "            {\n" + 
                "                \"species\": \"Carnivine\",\n" + 
                "                \"level\": 11,\n" + 
                "                \"moves\": [\n" + 
                "                    \"Vine Whip\",\n" + 
                "                    \"Bullet Seed\",\n" + 
                "                    \"Bug Bite\",\n" + 
                "                    \"Synthesis\"\n" + 
                "                ],\n" + 
                "                \"gender\": \"Female\",\n" + 
                "                \"ability\": \"Levitate\",\n" + 
                "                \"nature\": \"Adamant\",\n" + 
                "                \"item\": \"LEFTOVERS\",\n" + 
                "                \"evs\": {\n" + 
                "                    \"attack\": 100\n" + 
                "                }\n" + 
                "            }\n" + 
                "        ]\n" + 
                "    ],\n" + 
                "    \"GlobalRegionEntryStage\": [\n" + 
                "        314,\n" + 
                "        46,\n" + 
                "        756,\n" + 
                "        326,\n" + 
                "        53,\n" + 
                "        763\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"I need to be ready for anything! These Pokémon are vicious!\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"GlobalRegionEntryStage\": [\n" + 
                "        323,\n" + 
                "        51,\n" + 
                "        731,\n" + 
                "        329,\n" + 
                "        61,\n" + 
                "        722\n" + 
                "    ],\n" + 
                "    \"NoExperienceWildPokemonBattleStage\": [\n" + 
                "        326.5,\n" + 
                "        53.5,\n" + 
                "        729.5,\n" + 
                "        180,\n" + 
                "        [\n" + 
                "            {\n" + 
                "                \"species\": \"Nuzleaf\",\n" + 
                "                \"level\": 10,\n" + 
                "                \"moves\": [\n" + 
                "                    \"Razor Leaf\",\n" + 
                "                    \"Leaf Blade\",\n" + 
                "                    \"Brick Break\",\n" + 
                "                    \"Rock Tomb\"\n" + 
                "                ],\n" + 
                "                \"gender\": \"Male\",\n" + 
                "                \"ability\": \"Chlorophyll\",\n" + 
                "                \"nature\": \"Adamant\",\n" + 
                "                \"item\": \"MIRACLE_SEED\",\n" + 
                "                \"evs\": {\n" + 
                "                    \"speed\": 50,\n" + 
                "                    \"attack\": 50\n" + 
                "                }\n" + 
                "            }\n" + 
                "        ]\n" + 
                "    ],\n" + 
                "    \"GlobalRegionEntryStage\": [\n" + 
                "        323,\n" + 
                "        51,\n" + 
                "        731,\n" + 
                "        329,\n" + 
                "        61,\n" + 
                "        722\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"The Pokémon feel cornered! They're going to attack!\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"GlobalRegionEntryStage\": [\n" + 
                "        349,\n" + 
                "        46,\n" + 
                "        790,\n" + 
                "        355,\n" + 
                "        56,\n" + 
                "        797\n" + 
                "    ],\n" + 
                "    \"NoExperienceWildPokemonBattleStage\": [\n" + 
                "        352.5,\n" + 
                "        48.5,\n" + 
                "        795.5,\n" + 
                "        165,\n" + 
                "        [\n" + 
                "            {\n" + 
                "                \"species\": \"Combee\",\n" + 
                "                \"level\": 11,\n" + 
                "                \"moves\": [\n" + 
                "                    \"Gust\",\n" + 
                "                    \"Bug Bite\"\n" + 
                "                ],\n" + 
                "                \"gender\": \"Male\",\n" + 
                "                \"ability\": \"Hustle\",\n" + 
                "                \"nature\": \"Jolly\",\n" + 
                "                \"evs\": {\n" + 
                "                    \"speed\": 50,\n" + 
                "                    \"attack\": 50,\n" + 
                "                    \"defense\": 50,\n" + 
                "                    \"special_defense\": 50,\n" + 
                "                    \"hp\": 50\n" + 
                "                }\n" + 
                "            }\n" + 
                "        ]\n" + 
                "    ],\n" + 
                "    \"GlobalRegionEntryStage\": [\n" + 
                "        349,\n" + 
                "        46,\n" + 
                "        790,\n" + 
                "        355,\n" + 
                "        56,\n" + 
                "        797\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"Pokémon are jumping away from the fire and into me!\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"GlobalRegionEntryStage\": [\n" + 
                "        287,\n" + 
                "        42,\n" + 
                "        723,\n" + 
                "        280,\n" + 
                "        52,\n" + 
                "        731\n" + 
                "    ],\n" + 
                "    \"NoExperienceWildPokemonBattleStage\": [\n" + 
                "        284.5,\n" + 
                "        43.0,\n" + 
                "        725.5,\n" + 
                "        0,\n" + 
                "        [\n" + 
                "            {\n" + 
                "                \"species\": \"Beedrill\",\n" + 
                "                \"level\": 11,\n" + 
                "                \"moves\": [\n" + 
                "                    \"Poison Jab\",\n" + 
                "                    \"X-Scissor\",\n" + 
                "                    \"Aerial Ace\",\n" + 
                "                    \"Brick Break\"\n" + 
                "                ],\n" + 
                "                \"gender\": \"Male\",\n" + 
                "                \"ability\": \"Sniper\",\n" + 
                "                \"nature\": \"Jolly\",\n" + 
                "                \"item\": \"KINGS_ROCK\",\n" + 
                "                \"evs\": {\n" + 
                "                    \"speed\": 100,\n" + 
                "                    \"attack\": 50\n" + 
                "                }\n" + 
                "            }\n" + 
                "        ]\n" + 
                "    ],\n" + 
                "    \"GlobalRegionEntryStage\": [\n" + 
                "        287,\n" + 
                "        42,\n" + 
                "        723,\n" + 
                "        280,\n" + 
                "        52,\n" + 
                "        731\n" + 
                "    ],\n" + 
                "    \"DialogStage\": [\n" + 
                "        {\n" + 
                "            \"[PLAYER]\": \"I'm surrounded by flames and wild Pokémon!\"\n" + 
                "        }\n" + 
                "    ],\n" + 
                "    \"CompleteQuestStage\": [\n" + 
                "        \n" + 
                "    ]\n" + 
                "}");
    }
    
    static void print(String s) throws Exception{
        System.out.println(new JsonParser(new ByteArrayInputStream(s.getBytes("UTF-16"))).parse());
    }

}