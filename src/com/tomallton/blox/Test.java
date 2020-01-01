package com.tomallton.blox;

import java.io.ByteArrayInputStream;

import com.tomallton.blox.jsonparser.JsonParser;

public class Test {

    public static void main(String[] args) throws Exception {

        System.out.println(new JsonParser(new ByteArrayInputStream(("{\"RegionEntryStage\":[743,24,1759,741,27,1760],\"RequirePokemonStage\":[\"Slowpoke\"],\"CompletedOtherQuestProgress\":[\"reel_it_in\"],\"UseMoveStage\":[743,24,1759,741,27,1760,\"Slack Off\",\"The water is teeming with Pokemon...\",\"A Slowpoke could fish here.\"],\"NotCompletedQuestProgressStage\":[4,0],\"CompleteQuestStage\":[],\"GlobalHologramStage\":[742.5,26.0,1759.5,\"&a&lFishing Spot\",\"&3&lYou can fish for Pokemon here.\"],\"FreezeStage\":[],\"TimedStage\":[1.5],\"SendMessageStage\":[\"...\"],\"TimedStage\":[1.5],\"SendMessageStage\":[\"...\"],\"TimedStage\":[1.5],\"SendMessageStage\":[\"...\"],\"TimedStage\":[1.0],\"SendMessageStage\":[\"Oh, a bite!\"],\"UnfreezeStage\":[],\"CompleteQuestStage\":[]}").getBytes())).parse());

    }

}