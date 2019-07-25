package com.stackroute.Muzix.Exceptions;
public class TrackExist extends Exception {
        private String message;
        public TrackExist(){
        }

        public TrackExist(String message) {
            super(message);
            this.message=message;
        }
}
