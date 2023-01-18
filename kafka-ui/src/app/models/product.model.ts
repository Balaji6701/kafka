export interface Product{
    id:string,
    productId: string,
    productName: string,
    price: number,
    productCategory: string,
    active:boolean,
    brand:string,
    createdAt: Date,
    createdBy: string,
    lastModifiedAt: Date,
    lastModifiedBy: string,
    approvalStatus: string,
}