package com.chan.danny.bands;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by danny on 3/13/16.
 */
public class Exercise {

    public static class Builder {
        private int mNumSets;
        private Set<Integer> mColors;
        private String mName;

        public Builder() {
            mColors = new HashSet<>();
        }

        public Builder addColor(Integer c)
        {
            mColors.add(c);
            return this;
        }

        public Builder setSets(int sets)
        {
            mNumSets = sets;
            return this;
        }

        public Builder setName(String name)
        {
            mName = name;
            return this;
        }

        public Exercise build() {
            Exercise e = new Exercise();
            e.mNumSets = mNumSets;
            e.mColors = mColors;
            e.mName = mName;
            return e;
        }
    }

    private int mNumSets;
    private Set<Integer> mColors;
    private String mName;

    private Exercise() {

    }

    public int getNumSets() {
        return mNumSets;
    }

    public Set<Integer> getColors() {
        return mColors;
    }

    public String getName() {
        return mName;
    }
}
