/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
                words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix)
    {
        // 1 If prefix is empty return a randomly selected word from the words ArrayList
        if(prefix.equals(""))
        {
            Random randomIndex= new Random();
            return words.get(randomIndex.nextInt(words.size()));
        }
        /** 2. Otherwise, perform a binary search over the words ArrayList
         *     until you find a word that starts with the given prefix and return it.
         */
        else
        {
            return binarySearch(prefix);
        }


        //return null;
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        String selected = null;
        return selected;
    }

    private String binarySearch(String prefix) {

        String dictionaryWord;
        int low = 0;
        int high = words.size() - 1;
        while (high >= low) {
            int middle = (high + low) / 2;
            dictionaryWord = words.get(middle);
            if (dictionaryWord.startsWith(prefix)) {
                // if words exist with given prefix return it to getAnyWordStartWith
                return dictionaryWord;
            }
            if (dictionaryWord.compareTo(prefix) < 0) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        // If no such word exists, return null
        return null;
    }
}
