export class UploadItem {
    constructor (
        public title:string,
        public description:string,
        public price:Number,
        public category_id:Number,
        public photo:string,
        public user_id:Number
    ) {  }
  }