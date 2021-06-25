package com.svarks.trueherb.rest.controller;

import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.svarks.trueherb.common.TrueherbConstants;
import com.svarks.trueherb.dao.entity.Batch;
import com.svarks.trueherb.dao.entity.FlightSeaTimings;
import com.svarks.trueherb.dao.entity.LegalEntity;
import com.svarks.trueherb.dao.entity.PortEntity;
import com.svarks.trueherb.dao.entity.ProductQuantity;
import com.svarks.trueherb.dao.entity.Products;
import com.svarks.trueherb.dao.entity.ProductsFileInfo;
import com.svarks.trueherb.dao.entity.RoadTimings;
import com.svarks.trueherb.dao.entity.TransportMode;
import com.svarks.trueherb.dao.entity.UserEntity;
import com.svarks.trueherb.dao.entity.UserProfileEntity;
import com.svarks.trueherb.dao.entity.Warehouse;
import com.svarks.trueherb.dao.service.BatchDao;
import com.svarks.trueherb.dao.service.FlightAirDao;
import com.svarks.trueherb.dao.service.LegalEntityDao;
import com.svarks.trueherb.dao.service.PortEntityDao;
import com.svarks.trueherb.dao.service.ProductQuantityDao;
import com.svarks.trueherb.dao.service.ProductsDao;
import com.svarks.trueherb.dao.service.ProductsFileDao;
import com.svarks.trueherb.dao.service.RoadTimeDao;
import com.svarks.trueherb.dao.service.TransportModeDao;
import com.svarks.trueherb.dao.service.UserProfileDao;
import com.svarks.trueherb.dao.service.UserServiceDao;
import com.svarks.trueherb.dao.service.WarehouseDao;
import com.svarks.trueherb.request.model.BatchRequest;
import com.svarks.trueherb.request.model.LegalEntityRequest;
import com.svarks.trueherb.request.model.MailerRequest;
import com.svarks.trueherb.request.model.ModeTimingsRequest;
import com.svarks.trueherb.request.model.NewUserRequest;
import com.svarks.trueherb.request.model.PortRequest;
import com.svarks.trueherb.request.model.ProdQuantityRequest;
import com.svarks.trueherb.request.model.ProductFilesInfo;
import com.svarks.trueherb.request.model.ProductRequest;
import com.svarks.trueherb.request.model.RoadTimeRequest;
import com.svarks.trueherb.request.model.TransportModeRequest;
import com.svarks.trueherb.request.model.UserApproveRequest;
import com.svarks.trueherb.request.model.WarehouseRequest;
import com.svarks.trueherb.response.model.BaseResponse;
import com.svarks.trueherb.response.model.BatchListResponse;
import com.svarks.trueherb.response.model.FlightSeaResponse;
import com.svarks.trueherb.response.model.LegalEntityResponse;
import com.svarks.trueherb.response.model.NewUserResponse;
import com.svarks.trueherb.response.model.PortResponse;
import com.svarks.trueherb.response.model.ProdQuantResponse;
import com.svarks.trueherb.response.model.ProductListResponse;
import com.svarks.trueherb.response.model.ProductQuantityResponse;
import com.svarks.trueherb.response.model.RoadTimeResponse;
import com.svarks.trueherb.response.model.TransportModeResponse;
import com.svarks.trueherb.response.model.UserListResponse;
import com.svarks.trueherb.response.model.WarehouseResponse;
import com.svarks.trueherb.service.IQueryService;
import com.svarks.trueherb.service.TrueherbWareshouseService;

@CrossOrigin
@RestController
public class AdminController {
	
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	UserServiceDao userService;

	@Autowired
	UserProfileDao profileService;
	
	@Autowired
	WarehouseDao warehouseDao;
	
	@Autowired
	ProductsDao productDao;
	
	@Autowired
	ProductsFileDao productFileDao;
	
	@Autowired
	TrueherbWareshouseService trueherbService;
	
	@Autowired
	ProductQuantityDao prodQuantityDao;
	
	@Autowired
	LegalEntityDao legalEntityDao;
	
	@Autowired
	IQueryService queryService;
	
	@Autowired
	BatchDao batchService;
	
	@Autowired
	PortEntityDao portService;
	
	@Autowired
	FlightAirDao modeTimingsService;
	
	@Autowired
	RoadTimeDao roadTimingsService;
	
	
	@Autowired
	TransportModeDao transportModeService;
	
	/**
	 * Method is used to add and update warehouse information
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.WAREHOUSE_API, produces = TrueherbConstants.APPLICATION_JSON)
	public BaseResponse addWarehouse(@RequestBody WarehouseRequest warehouseReq) {
		BaseResponse res = new BaseResponse();
		try {
			log.info("Adding warehouse==>"+warehouseReq.getWarehouseName());
			if(warehouseReq.getWarehouseName() != null) {
				if(warehouseReq.getWid() ==0 && warehouseDao.findByWarehouse(warehouseReq.getWarehouseName())) {
					log.info("Warehouse already exists==>"+warehouseReq.getWarehouseName());
						res.setErrorMessage("Warehouse already exists");
	            	   res.setStatus(200);
	            	   res.setStatusMessage("error");
				}else if(warehouseReq.getWid() !=0){
					warehouseDao.getOne(warehouseReq.getWid());
					Warehouse warehouse =warehouseDao.findById(warehouseReq.getWid()).get();
					log.info("update request==>",warehouse);
					warehouse.setAddress(warehouseReq.getAddress());
					warehouse.setPincode(warehouseReq.getPincode());
					warehouse.setWarehouseName(warehouseReq.getWarehouseName());
					warehouseDao.save(warehouse);
					res.setStatus(TrueherbConstants.SUCCESS_STATUS);
	            	   res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
	            	   res.setSuccessMessage("Updated Successfully..!");
				}else {
					warehouseDao.save(warehouseParser(warehouseReq));
					res.setStatus(TrueherbConstants.SUCCESS_STATUS);
            	   res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
            	   res.setSuccessMessage("Added Successfully..!");
			}
			
            	   
            	   
               }else {
            	   res.setErrorMessage("Invalid payload");
            	   res.setStatus(200);
            	   res.setStatusMessage("error");
               }

		} catch (Exception e) {
			res=trueherbService.generateResponse(false, TrueherbConstants.INTERNAL_SERVER_ERROR_MSG);
			log.error("Error occured while adding/updating warehouse details:",e);
		}
		return res;
	}
	
	/**
	 * Method is fetch the warehouse information
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping(value = TrueherbConstants.WAREHOUSE_API, produces = TrueherbConstants.APPLICATION_JSON)
	public WarehouseResponse getWarehouseData() {
		WarehouseResponse res = new WarehouseResponse();
		try {
			res.setStatus(TrueherbConstants.SUCCESS_STATUS);
			res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
			res.setSuccessMessage("Added Successfully..!");
			res.setWarhouseList(warehouseDao.findAll());
		} catch (Exception e) {
			res=(WarehouseResponse) trueherbService.generateResponse(false, TrueherbConstants.INTERNAL_SERVER_ERROR_MSG);
			res.setStatus(TrueherbConstants.INTERNAL_SERVER_ERROR);
			log.error("Error occured while fetching warehouse details:",e);
		}
		return res;
	}
	
	
	/**
	 * Method is add update the product information
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.PRODUCT_API, produces = TrueherbConstants.APPLICATION_JSON)
	public BaseResponse productData(@RequestBody ProductRequest productRequest) {
		BaseResponse res = new BaseResponse();
		try {
			if(productRequest.getPid() ==0) {
				//Add product
				log.info("Product details request==>" + productRequest);
				if (!productDao.isProductExists(productRequest.getName(), productRequest.getProductCode())) {
					productDao.save(getProductsDao(productRequest));
					res = trueherbService.generateResponse(true, TrueherbConstants.ADD_SUCCESS);
				} else {
					res = trueherbService.generateResponse(false, TrueherbConstants.DUPLICATE_PRODUCT);
				}
			} else if (productRequest.getPid() > 0) {
				//Update Product
				Products products=productDao.findById(productRequest.getPid()).get();
				  //List<ProductsFileInfo> prodFileInfo = (List<ProductsFileInfo>) productFileDao.findById(productRequest.getPid()).get();
				//  products.getProductFileInfoList().clear();
				 /* if(productRequest.getProductFileInfoList() !=null && !productRequest.getProductFileInfoList().isEmpty()) {
					  boolean updateFlag=true;
					  for(ProductFilesInfo prodFile:productRequest.getProductFileInfoList()) {
							ProductsFileInfo fileInfo = new ProductsFileInfo();
							if(!prodFileInfo.isEmpty()) {
								updateFlag=false;
								  for(ProductsFileInfo pinfo:prodFileInfo) {
									      if(pinfo.getFid() == prodFile.getFid()) {
									    	 // productFileDao.getOne(id)
									    	  updateFlag=true;
									      }
									  
								  }
							}else {
									 if(!updateFlag) {
									  fileInfo.setDocDescription(prodFile.getDocDescription());
										fileInfo.setDocName(prodFile.getDocName());
										fileInfo.setImageDescription(prodFile.getImageDescription());
										fileInfo.setImageName(prodFile.getImageName());
										prodFileInfo.add(fileInfo);
									 }
								  }
						
					//	prodList.add(fileInfo);
						
					  
					   
					  }
				  }  */
				  
				//  products.setProductFileInfoList(prodFileInfo);
					products=getProductsDao(productRequest);
					productDao.save(products);
					res = trueherbService.generateResponse(true, TrueherbConstants.UPDATE_SUCCESS);
				
			}else {
				res=trueherbService.generateResponse(false, TrueherbConstants.INVALID_REQUEST);
			}
		} catch (Exception e) {
			log.error("Error occured while adding/updating productData details:",e);
			res=trueherbService.generateResponse(false, TrueherbConstants.INTERNAL_SERVER_ERROR_MSG);
			res.setStatus(TrueherbConstants.INTERNAL_SERVER_ERROR);
		}
		return res;
	}
	
	
	private Products getProductsDao(ProductRequest productReq) {
		Products products = new Products();
		products.setDescription(productReq.getDescription());
		products.setProductCode(productReq.getProductCode());
		products.setProductName(productReq.getName());
		products.setModifiedDate(new Date());
		if(productReq.getPid() !=0) {
			products.setPid(productReq.getPid());
		}else {
			products.setCreatdDate(new Date());
			
			if(productReq.getProductFileInfoList() != null && !productReq.getProductFileInfoList().isEmpty()) {
				List<ProductsFileInfo> prodList = new LinkedList<>();
				for(ProductFilesInfo prodFile:productReq.getProductFileInfoList()) {
					ProductsFileInfo fileInfo = new ProductsFileInfo();
				
				fileInfo.setDocDescription(prodFile.getDocDescription());
				fileInfo.setDocName(prodFile.getDocName());
				fileInfo.setImageDescription(prodFile.getImageDescription());
				fileInfo.setImageName(prodFile.getImageName());
				prodList.add(fileInfo);
				}
				products.setProductFileInfoList(prodList);
				
			}

		}
		
		return products;
	}
	
	/**
	 * Method is fetch the warehouse information
	 * 
	 * @param user
	 * @return
	 */
//	@GetMapping(value = TrueherbConstants.PRODUCT_API, produces = TrueherbConstants.APPLICATION_JSON)
//	public ProductListResponse getProductInfo() {
//		ProductListResponse res = new ProductListResponse();
//		try {
//			res.setStatus(TrueherbConstants.SUCCESS_STATUS);
//			res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
//			res.setSuccessMessage("Added Successfully..!");
//			res.setProductList(productDao.findAll());
//		} catch (Exception e) {
//			log.error("Error occured while fetching warehouse details:",e);
//		}
//		return res;
//	}
	
	/**
	 * Method is used to add,update and delete product quantity information
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.PRODUCT_QUANTITY, produces = TrueherbConstants.APPLICATION_JSON)
	public BaseResponse addProductQuantity(@RequestBody ProdQuantityRequest prodQuantiyReq) {
		BaseResponse res = new BaseResponse();
		try {
			//prodQuantityDao
			log.info("Adding product quantity==>"+prodQuantiyReq.getWarehouseName());
			if(prodQuantiyReq.getWarehouseName() != null) {
				if(prodQuantiyReq.isDelete()) {
					log.info("quantity Deleting ==>"+prodQuantiyReq.getProductName());
					ProductQuantity prodQuan = prodQuantityDao.findById(prodQuantiyReq.getQid()).get();
					prodQuantityDao.delete(prodQuan);
					res = trueherbService.generateResponse(true, TrueherbConstants.DELETED_SUCCESSFULLY);
				}else if(prodQuantiyReq.getQid() ==0 ) {
					log.info("quantity adding ==>"+prodQuantiyReq.getWarehouseName());
					if(prodQuantityDao.isProductExists(prodQuantiyReq.getProductName(),prodQuantiyReq.getWarehouseName())) {
						res = trueherbService.generateResponse(true, TrueherbConstants.DUPLICATE_PRODUCT);
					}else {
					prodQuantityDao.save(prodQuantityParser(prodQuantiyReq));
					res = trueherbService.generateResponse(true, TrueherbConstants.ADDED_SUCCESSFULLY);
					}
				} else if (prodQuantiyReq.getQid() != 0) {
					ProductQuantity prodQuan = prodQuantityDao.findById(prodQuantiyReq.getQid()).get();
					log.info("update request==>", prodQuan);
					prodQuan.setBatch(prodQuantiyReq.getBatch());
					prodQuan.setBatchId(prodQuantiyReq.getBatchId());
					prodQuan.setModifiedDate(new Date());
					prodQuan.setProductId(prodQuantiyReq.getProductId());
					prodQuan.setProductName(prodQuantiyReq.getProductName());
					prodQuan.setWarehouseId(prodQuantiyReq.getWarehouseId());
					prodQuan.setWarehouseName(prodQuantiyReq.getWarehouseName());
					prodQuan.setExpiryDate(trueherbService.convertStringToDate(prodQuantiyReq.getExpiryDate()));
					prodQuan.setQuantity(prodQuantiyReq.getQuantity());
					prodQuantityDao.save(prodQuan);
					res = trueherbService.generateResponse(true, TrueherbConstants.UPDATE_SUCCESS);
				}
				
			}else {
            	   res = trueherbService.generateResponse(false, TrueherbConstants.INVALID_PAYLOAD);
               }

		} catch (Exception e) {
			res=trueherbService.generateResponse(false, TrueherbConstants.INTERNAL_SERVER_ERROR_MSG);
			res.setStatus(TrueherbConstants.INTERNAL_SERVER_ERROR);
			log.error("Error occured while adding/updating warehouse details:",e);
		}
		return res;
	}
	
	/**
	 * Method is fetch the warehouse information
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping(value = TrueherbConstants.PRODUCT_QUANTITY_API, produces = TrueherbConstants.APPLICATION_JSON)
	public ProductQuantityResponse getQuantityAll() {
		ProductQuantityResponse res = new ProductQuantityResponse();
		try {
			res.setStatus(TrueherbConstants.SUCCESS_STATUS);
			res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
			res.setProdQuantityList(prodQuantityDao.findAll());
		} catch (Exception e) {
			log.error("Error occured while fetching warehouse details:",e);
		}
		return res;
	}
	
	
	
	/**
	 * Method is used to add ,update and delete legal entity information
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.LEGAL_ENTITY, produces = TrueherbConstants.APPLICATION_JSON)
	public BaseResponse legalEntityProcess(@RequestBody LegalEntityRequest legalEntityReq) {
		BaseResponse res = new BaseResponse();
		try {
			//prodQuantityDao
			log.info("Adding Legal entity name==>"+legalEntityReq.getEntityName());
			log.info("legal entity request==>",legalEntityReq);
			System.out.println("request==>"+legalEntityReq);
			if(legalEntityReq.getEntityName() != null) {
				if(legalEntityReq.isDelete()) {
					log.info("quantity Deleting ==>"+legalEntityReq.getEntityName());
					LegalEntity legalEntity = legalEntityDao.findById(legalEntityReq.getLegalEntityId()).get();
					legalEntityDao.delete(legalEntity);
					res = trueherbService.generateResponse(true, TrueherbConstants.DELETED_SUCCESSFULLY);
				}else if(legalEntityReq.getLegalEntityId() ==0 ) {
					if(legalEntityDao.isLegalEntityExists(legalEntityReq.getEntityName())){
						res = trueherbService.generateResponse(false, TrueherbConstants.DUPLICATE_ENTITY);
					}else {
					log.info("entity adding ==>"+legalEntityReq.getEntityName());
					legalEntityDao.save(legalEntityParser(legalEntityReq));
					res = trueherbService.generateResponse(true, TrueherbConstants.ADDED_SUCCESSFULLY);
					}
				} else if (legalEntityReq.getLegalEntityId() != 0) {
					LegalEntity lEntity = legalEntityDao.findById(legalEntityReq.getLegalEntityId()).get();
					log.info("update request==>", lEntity);
					lEntity.setCoutry(legalEntityReq.getCoutry());
					lEntity.setModifiedDate(new Date());
					lEntity.setEntityName(legalEntityReq.getEntityName());
					lEntity.setPincode(legalEntityReq.getPincode());
					legalEntityDao.save(lEntity);
					res = trueherbService.generateResponse(true, TrueherbConstants.UPDATE_SUCCESS);
				}
				
			}else {
            	   res = trueherbService.generateResponse(false, TrueherbConstants.INVALID_PAYLOAD);
               }

		} catch (Exception e) {
			res=trueherbService.generateResponse(false, TrueherbConstants.INTERNAL_SERVER_ERROR_MSG);
			log.error("Error occured while adding/updating warehouse details:",e);
		}
		return res;
	}
	
	
	/**
	 * Method is fetch the legal entity information
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping(value = TrueherbConstants.LEGAL_ENTITY, produces = TrueherbConstants.APPLICATION_JSON)
	public LegalEntityResponse getLegalEntityAll() {
		LegalEntityResponse res = new LegalEntityResponse();
		try {
			res.setStatus(TrueherbConstants.SUCCESS_STATUS);
			res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
			res.setLegalEntityList(legalEntityDao.findAll());
		} catch (Exception e) {
			log.error("Error occured while fetching Legal entity details:",e);
		}
		return res;
	}
	
	
	/**
	 * Method is fetch the warehouse information
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping(value = TrueherbConstants.TRUE_HERB_GET_API, produces = TrueherbConstants.APPLICATION_JSON)
	public ProdQuantResponse getUIResponse() {
		ProdQuantResponse res = new ProdQuantResponse();
		try {
			res.setStatus(TrueherbConstants.SUCCESS_STATUS);
			res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
			res.setProductList(productDao.findAll());
			res.setWarhouseList(warehouseDao.findAll());
			res.setBatchList(batchService.findAll());
		} catch (Exception e) {
			log.error("Error occured while fetching getUIResponse details:",e);
		}
		return res;
	}
	
	/**
	 * Method is fetch the legal entity information
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping(value = TrueherbConstants.GET_USERS_LIST, produces = TrueherbConstants.APPLICATION_JSON)
	public UserListResponse getUserInfoList() {
		UserListResponse res = new UserListResponse();
		try {
			res.setStatus(TrueherbConstants.SUCCESS_STATUS);
			res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
			res.setUserProfileDataList(queryService.getUserInfoList());
		log.info("result data===>",res.getUserProfileDataList());
		} catch (Exception e) {
			log.error("Error occured while fetching getUserInfoList:",e);
		}
		return res;
	}
	
	
	/**
	 * Method is fetch the legal entity information
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.APPROVE_USER, produces = TrueherbConstants.APPLICATION_JSON)
	public BaseResponse provideUserAccess(@RequestBody UserApproveRequest approveRequest) {
		BaseResponse res = new BaseResponse();
		try {
			
			UserEntity user = userService.getUserDataByUsername(approveRequest.getUsername());
			if(user !=null && user.getEmailId() != null) {
				userService.updateIsEmailVerified(approveRequest.getUsername());
				MailerRequest mailRequest = new MailerRequest();
				mailRequest.setButtonName(TrueherbConstants.LOGIN_BUTTON_NAME);
				mailRequest.setP(TrueherbConstants.LOGIN_CONTENT);
				mailRequest.setName(user.getUsername());
				mailRequest.setSubject(TrueherbConstants.LOGIN_SUBJECT);
				mailRequest.setUrl(TrueherbConstants.LOGIN_URL);
				mailRequest.setLabel1(TrueherbConstants.USERNAME);
				mailRequest.setP1(user.getUsername());
				//mailRequest.setLabel2(TrueherbConstants.PASSWORD);
				//mailRequest.setP2(getBase64DecryptionPwd(user.getPassword()));
				mailRequest.setTo(user.getEmailId());
				trueherbService.sendMail(mailRequest);
				res=trueherbService.generateResponse(true, TrueherbConstants.SUCCESS_MSG);
			}else {
				res=trueherbService.generateResponse(false, TrueherbConstants.UNAUTHRORIZED_REQUEST);		
						
			}
			
		} catch (Exception e) {
			log.error("Error occured while provideUserAccess:",e);
		}
		return res;
	}
	
	
	

	/**
	 * Method is used to add ,update and delete Batch details information
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.BATCH_OPERATION, produces = TrueherbConstants.APPLICATION_JSON)
	public BaseResponse batchInfo(@RequestBody BatchRequest batchRequest) {
		BaseResponse res = new BaseResponse();
		try {
			//prodQuantityDao
			log.info("request==>"+batchRequest);
			if(batchRequest.getBatchName() != null) {
				if(batchRequest.isDelete()) {
					log.info("Batch Deleting ==>"+batchRequest.getBatchName());
					Batch batch = batchService.findById(batchRequest.getBid()).get();
					batchService.delete(batch);
					res = trueherbService.generateResponse(true, TrueherbConstants.DELETED_SUCCESSFULLY);
				}else if(batchRequest.getBid() ==0 ) {
					if(batchService.isBatchExists(batchRequest.getBatchName())){
						res = trueherbService.generateResponse(false, TrueherbConstants.DUPLICATE_BATCH_ENTITY);
					}else {
					log.info("batch adding ==>"+batchRequest.getBatchName());
					batchService.save(batchDataParser(batchRequest));
					res = trueherbService.generateResponse(true, TrueherbConstants.ADDED_SUCCESSFULLY);
					}
				} else if (batchRequest.getBid() != 0) {
					Batch batch1 = batchService.findById(batchRequest.getBid()).get();
					log.info("update request==>", batch1);
					batch1.setBatchName(batchRequest.getBatchName());
					batch1.setModifiedDate(new Date());
					batchService.save(batch1);
					res = trueherbService.generateResponse(true, TrueherbConstants.UPDATE_SUCCESS);
				}
				
			}else {
            	   res = trueherbService.generateResponse(false, TrueherbConstants.INVALID_PAYLOAD);
               }

		} catch (Exception e) {
			res=trueherbService.generateResponse(false, TrueherbConstants.INTERNAL_SERVER_ERROR_MSG);
			log.error("Error occured while adding/updating warehouse details:",e);
		}
		return res;
	}
	
	
	/**
	 * Method is fetch the batch details information
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping(value = TrueherbConstants.GET_BATCH_INFO, produces = TrueherbConstants.APPLICATION_JSON)
	public BatchListResponse getBatchInfoList() {
		BatchListResponse res = new BatchListResponse();
		try {
			res.setStatus(TrueherbConstants.SUCCESS_STATUS);
			res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
			res.setBatchList(batchService.findAll());
		} catch (Exception e) {
			log.error("Error occured while fetching getBatchInfoList:",e);
		}
		return res;
	}
	
	
	
	/**
	 * Method is used to add ,update and delete Batch details information
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.PORT_OPERATION, produces = TrueherbConstants.APPLICATION_JSON)
	public BaseResponse portOperation(@RequestBody PortRequest portRequest) {
		BaseResponse res = new BaseResponse();
		try {
			//prodQuantityDao
			log.info("request==>"+portRequest);
			if(portRequest.getFromCountry() != null && portRequest.getToCountry() !=null) {
				if(portRequest.isDelete()) {
					log.info("Port Deleting ==>"+portRequest.getFromCountry());
					PortEntity port = portService.findById(portRequest.getPortid()).get();
					portService.delete(port);
					res = trueherbService.generateResponse(true, TrueherbConstants.DELETED_SUCCESSFULLY);
				}else if(portRequest.getPortid() ==0 ) {
					if(portService.isPortExists(portRequest.getFromCountry(), portRequest.getToCountry(), portRequest.getFromPort(),
							portRequest.getToPort())){
						res = trueherbService.generateResponse(false, TrueherbConstants.DUPLICATE_PORT_ENTITY);
					}else {
					log.info("Port adding ==>"+portRequest.getFromCountry());
					portService.save(portDataParser(portRequest));
					res = trueherbService.generateResponse(true, TrueherbConstants.ADDED_SUCCESSFULLY);
					}
				} else if (portRequest.getPortid() != 0) {
					PortEntity port = portService.findById(portRequest.getPortid()).get();
					log.info("update request==>", port);
					port.setModifiedDate(new Date());
					port.setFromCountry(portRequest.getFromCountry());
					port.setFromPort(portRequest.getFromPort());
					port.setToCountry(portRequest.getToCountry());
					port.setToPort(portRequest.getToPort());
					portService.save(port);
					res = trueherbService.generateResponse(true, TrueherbConstants.UPDATE_SUCCESS);
				}
				
			}else {
            	   res = trueherbService.generateResponse(false, TrueherbConstants.INVALID_PAYLOAD);
               }

		} catch (Exception e) {
			res=trueherbService.generateResponse(false, TrueherbConstants.INTERNAL_SERVER_ERROR_MSG);
			log.error("Error occured while adding/updating port details:",e);
		}
		return res;
	}
	

	/**
	 * Method is fetch the port details information
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping(value = TrueherbConstants.GET_PORT_INFO, produces = TrueherbConstants.APPLICATION_JSON)
	public PortResponse getPortInfoList() {
		PortResponse res = new PortResponse();
		try {
			res.setStatus(TrueherbConstants.SUCCESS_STATUS);
			res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
			res.setPortEntityList(portService.findAll());
		} catch (Exception e) {
			log.error("Error occured while fetching getPortInfoList:",e);
		}
		return res;
	}
	
	
	
	/**
	 * Method is used to add ,update and delete Batch details information
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.MODE_TIMINGS, produces = TrueherbConstants.APPLICATION_JSON)
	public BaseResponse modeTimingsOperation(@RequestBody ModeTimingsRequest modeTimingsRequest) {
		BaseResponse res = new BaseResponse();
		try {
			//prodQuantityDao
			log.info("request==>"+modeTimingsRequest);
			if(modeTimingsRequest.getFromCountry() != null && modeTimingsRequest.getToCountry() !=null) {
				if(modeTimingsRequest.isDelete()) {
					log.info("Port Deleting ==>"+modeTimingsRequest.getFromCountry());
					FlightSeaTimings port = modeTimingsService.findById(modeTimingsRequest.getFlightSeaId()).get();
					modeTimingsService.delete(port);
					res = trueherbService.generateResponse(true, TrueherbConstants.DELETED_SUCCESSFULLY);
				}else if(modeTimingsRequest.getFlightSeaId() ==0 ) {
					if(modeTimingsService.isTimingsExists(modeTimingsRequest.getFromCountry(), modeTimingsRequest.getToCountry(),
							modeTimingsRequest.getMode())){
						res = trueherbService.generateResponse(false, TrueherbConstants.DUPLICATE_PORT_ENTITY);
					}else {
					log.info("Mode timings adding ==>"+modeTimingsRequest.getFromCountry());
					modeTimingsService.save(modeTimingsParser(modeTimingsRequest));
					res = trueherbService.generateResponse(true, TrueherbConstants.ADDED_SUCCESSFULLY);
					}
				} else if (modeTimingsRequest.getFlightSeaId() != 0) {
					FlightSeaTimings flightSeaTimings = modeTimingsService.findById(modeTimingsRequest.getFlightSeaId()).get();
					log.info("update request==>", flightSeaTimings);
					flightSeaTimings.setAirTime(modeTimingsRequest.getAirTime());
					flightSeaTimings.setFromCountry(modeTimingsRequest.getFromCountry());
					flightSeaTimings.setMode(modeTimingsRequest.getMode());
					flightSeaTimings.setModifiedDate(new Date());
					flightSeaTimings.setSeaTime(modeTimingsRequest.getSeaTime());
					flightSeaTimings.setToCountry(modeTimingsRequest.getToCountry());
					modeTimingsService.save(flightSeaTimings);
					res = trueherbService.generateResponse(true, TrueherbConstants.UPDATE_SUCCESS);
				}
				
			}else {
            	   res = trueherbService.generateResponse(false, TrueherbConstants.INVALID_PAYLOAD);
               }

		} catch (Exception e) {
			res=trueherbService.generateResponse(false, TrueherbConstants.INTERNAL_SERVER_ERROR_MSG);
			log.error("Error occured while adding/updating modeTimingsOperation details:",e);
		}
		return res;
	}

	/**
	 * Method is fetch the mode timings details information
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping(value = TrueherbConstants.MODE_TIMINGS, produces = TrueherbConstants.APPLICATION_JSON)
	public FlightSeaResponse getModeTimingsList() {
		FlightSeaResponse res = new FlightSeaResponse();
		try {
			res.setStatus(TrueherbConstants.SUCCESS_STATUS);
			res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
			res.setFlightSeaTimingsList(modeTimingsService.findAll());
		} catch (Exception e) {
			log.error("Error occured while fetching getModeTimingsList:",e);
		}
		return res;
	}
	
	
	/**
	 * Method is used to add ,update and delete Road timings information
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.ROAD_TIMINGS, produces = TrueherbConstants.APPLICATION_JSON)
	public BaseResponse roadTimingsOperatio(@RequestBody RoadTimeRequest roadTimeRequest) {
		BaseResponse res = new BaseResponse();
		try {
			//prodQuantityDao
			log.info("request==>"+roadTimeRequest);
			if(roadTimeRequest.getKilometer() != null) {
				if(roadTimeRequest.isDelete()) {
					log.info("Road timings ==>"+roadTimeRequest.getKilometer());
					RoadTimings port = roadTimingsService.findById(roadTimeRequest.getTimingsId()).get();
					roadTimingsService.delete(port);
					res = trueherbService.generateResponse(true, TrueherbConstants.DELETED_SUCCESSFULLY);
				}else if(roadTimeRequest.getTimingsId() ==0 ) {
					if(roadTimingsService.isRoadTimeExists(roadTimeRequest.getKilometer())){
						res = trueherbService.generateResponse(false, TrueherbConstants.DUPLICATE_ROAD_ENTITY);
					}else {
					log.info("Mode timings adding ==>"+roadTimeRequest.getKilometer());
					roadTimingsService.save(roadTimingsParser(roadTimeRequest));
					res = trueherbService.generateResponse(true, TrueherbConstants.ADDED_SUCCESSFULLY);
					}
				} else if (roadTimeRequest.getTimingsId() != 0) {
					RoadTimings flightSeaTimings = roadTimingsService.findById(roadTimeRequest.getTimingsId()).get();
					log.info("update request==>", flightSeaTimings);
					flightSeaTimings.setModifiedDate(new Date());
					flightSeaTimings.setKilometer(roadTimeRequest.getKilometer());
					flightSeaTimings.setTimeInHrs(roadTimeRequest.getTimeInHrs());
					roadTimingsService.save(flightSeaTimings);
					res = trueherbService.generateResponse(true, TrueherbConstants.UPDATE_SUCCESS);
				}
				
			}else {
            	   res = trueherbService.generateResponse(false, TrueherbConstants.INVALID_PAYLOAD);
               }

		} catch (Exception e) {
			res=trueherbService.generateResponse(false, TrueherbConstants.INTERNAL_SERVER_ERROR_MSG);
			log.error("Error occured while adding/updating modeTimingsOperation details:",e);
		}
		return res;
	}
	
	
	/**
	 * Method is fetch the mode timings details information
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping(value = TrueherbConstants.ROAD_TIMINGS, produces = TrueherbConstants.APPLICATION_JSON)
	public RoadTimeResponse getRoadTimingsInfo() {
		RoadTimeResponse res = new RoadTimeResponse();
		try {
			res.setStatus(TrueherbConstants.SUCCESS_STATUS);
			res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
			res.setRoadTimeList(roadTimingsService.findAll());
		} catch (Exception e) {
			log.error("Error occured while fetching getModeTimingsList:",e);
		}
		return res;
	}
	
	
	/**
	 * Method is used to add ,update and delete transport mode information
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.TRANSPORT_MODE, produces = TrueherbConstants.APPLICATION_JSON)
	public BaseResponse transportMode(@RequestBody TransportModeRequest transportModeRequest) {
		BaseResponse res = new BaseResponse();
		try {
			//prodQuantityDao
			log.info("request==>"+transportModeRequest);
			if(transportModeRequest.getFromCountry() != null) {
				if(transportModeRequest.isDelete()) {
					log.info("Road timings ==>"+transportModeRequest.getFromCountry());
					TransportMode port = transportModeService.findById(transportModeRequest.getModeid()).get();
					transportModeService.delete(port);
					res = trueherbService.generateResponse(true, TrueherbConstants.DELETED_SUCCESSFULLY);
				}else if(transportModeRequest.getModeid() ==0 ) {
					if(transportModeService.isTransportModeExists(transportModeRequest.getFromCountry(), transportModeRequest.getToCountry())){
						res = trueherbService.generateResponse(false, TrueherbConstants.DUPLICATE_ROAD_ENTITY);
					}else {
					log.info("Mode timings adding ==>"+transportModeRequest);
					transportModeService.save(tranportModeParser(transportModeRequest));
					res = trueherbService.generateResponse(true, TrueherbConstants.ADDED_SUCCESSFULLY);
					}
				} else if (transportModeRequest.getModeid() != 0) {
					TransportMode tmode = transportModeService.findById(transportModeRequest.getModeid()).get();
					log.info("update request==>", tmode);
					tmode.setFromCountry(transportModeRequest.getFromCountry());
					tmode.setModeType(transportModeRequest.getModeType());
					tmode.setToCountry(transportModeRequest.getToCountry());
					tmode.setModifiedDate(new Date());
					transportModeService.save(tmode);
					res = trueherbService.generateResponse(true, TrueherbConstants.UPDATE_SUCCESS);
				}
				
			}else {
            	   res = trueherbService.generateResponse(false, TrueherbConstants.INVALID_PAYLOAD);
               }

		} catch (Exception e) {
			res=trueherbService.generateResponse(false, TrueherbConstants.INTERNAL_SERVER_ERROR_MSG);
			log.error("Error occured while adding/updating modeTimingsOperation details:",e);
		}
		return res;
	}
	
	
	/**
	 * Method is fetch the mode timings details information
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping(value = TrueherbConstants.TRANSPORT_MODE, produces = TrueherbConstants.APPLICATION_JSON)
	public TransportModeResponse getRoadTranportList() {
		TransportModeResponse res = new TransportModeResponse();
		try {
			res.setStatus(TrueherbConstants.SUCCESS_STATUS);
			res.setStatusMessage(TrueherbConstants.SUCCESS_MSG);
			res.setTransportModeList(transportModeService.findAll());
		} catch (Exception e) {
			res=(TransportModeResponse) trueherbService.generateResponse(false, TrueherbConstants.INTERNAL_SERVER_ERROR_MSG);
			log.error("Error occured while fetching getModeTimingsList:",e);
			
		}
		return res;
	}
	
	
	/**
	 * Method is used to new admin user
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value = TrueherbConstants.CREATE_NEW_ADMIN, produces = TrueherbConstants.APPLICATION_JSON)
	public BaseResponse registerNewUser(@RequestBody NewUserRequest user) {
		BaseResponse res = new BaseResponse();
		try {
			log.info("registering new User==>"+user.getCompanyEmailId());
               if(user != null && user.getCompanyEmailId() != null) {
            	   
            	     if( userService.findByUsername(user.getCompanyEmailId())) {
            	    	 res = trueherbService.generateResponse(false, TrueherbConstants.USERNAME_UNIQUE_MSG);
            	     }else {
            	    	saveUserDetails(user);
            	    	 profileService.save(userProfileParser(user));
              	    	 res = trueherbService.generateResponse(true, TrueherbConstants.ADMIN_CREATEION_SUCCESS);
            	    	 
            	     }
            	   
               }else {
      	    	 res = trueherbService.generateResponse(false, TrueherbConstants.INVALID_REQUEST);
      	     }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Method is used to new admin user
	 * 
	 * @param user
	 * @return
	 */
//	@PostMapping(value = TrueherbConstants.UPDATE_PROFILE, produces = TrueherbConstants.APPLICATION_JSON)
//	public BaseResponse updateProfile(@RequestBody NewUserRequest user) {
//		BaseResponse res = new BaseResponse();
//		try {
//			log.info("Updating the profile==>"+user.getCustomername());
//               if(user != null && user.getProfileId() !=0) {
//            	   
//            	   UserProfileEntity userProfile = profileService.findById(user.getProfileId()).get();
//            	    	 
//            	    	 userProfile.setAddress(user.getAddress());
//            	       	 userProfile.setCustomername(user.getCustomername());
//            	       	 userProfile.setDescription(user.getDescription());
//            	       	 userProfile.setDesignation(user.getDesignation());
//            	       	 userProfile.setLandline(user.getLandline());
//            	       	 userProfile.setMobilenumber(user.getMobilenumber());
//            	       	 userProfile.setModifiedDate(new Date());
//            	       	 userProfile.setPhonenumber(user.getPhonenumber());
//            	       	 userProfile.setPincode(user.getPincode());
//            	       	 userProfile.setUemailId(user.getUemailId());
//            	       	 userProfile.setUserType(TrueherbConstants.ADMIN);
//            	       	 userProfile.setWebsitelink(user.getWebsitelink());
//            	       	 
//            	       	profileService.save(userProfile);
//              	    	 res = trueherbService.generateResponse(true, TrueherbConstants.UPDATE_SUCCESS);
//            	   
//               }else {
//      	    	 res = trueherbService.generateResponse(false, TrueherbConstants.INVALID_REQUEST);
//      	     }
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return res;
//	}
//	
	
	private void saveUserDetails(NewUserRequest user) {
		UserEntity  userentity = new UserEntity();
		 userentity.setEmailVerified(true);
    	 userentity.setCreatedDate(new Date());
    	 userentity.setEmailId(user.getCompanyEmailId());
    	 userentity.setFirstTimeLogin(true);
    	 userentity.setModifiedDate(new Date());
    	 userentity.setUtype(TrueherbConstants.ADMIN);
    	// String pwd=getAlphaNumericString(6,user.getCompanyname());
    	 userentity.setPassword(user.getPassword());
    	 userentity.setUsername(user.getCompanyEmailId());
    	 userService.save(userentity);
    	 
    	 //Send Mail
    	 
    	 	MailerRequest mailRequest = new MailerRequest();
			mailRequest.setButtonName(TrueherbConstants.LOGIN_BUTTON_NAME);
			mailRequest.setP(TrueherbConstants.LOGIN_CONTENT);
			mailRequest.setName(user.getFirstname());
			mailRequest.setSubject(TrueherbConstants.ADMIN_CREATION_SUBJECT);
			mailRequest.setUrl(TrueherbConstants.LOGIN_URL);
			mailRequest.setLabel1(TrueherbConstants.USERNAME);
			mailRequest.setP1(user.getCompanyname());
			mailRequest.setLabel2(TrueherbConstants.PASSWORD);
			//mailRequest.setP2(pwd);
			mailRequest.setTo(user.getCompanyEmailId());
			trueherbService.sendMail(mailRequest);
    	 
    	 
	}
	
	private UserProfileEntity userProfileParser(NewUserRequest user) {
		UserProfileEntity userProfile=new UserProfileEntity();
   	 userProfile.setCompanyname(user.getCompanyname());
   	userProfile.setCompanyEmailId(user.getCompanyEmailId());
   	userProfile.setFirstname(user.getFirstname());
   	userProfile.setLastname(user.getLastname());
   	 userProfile.setCreatedDate(new Date());
   	 userProfile.setMobilenumber(user.getMobilenumber());
   	 userProfile.setModifiedDate(new Date());
   	 userProfile.setUserType(TrueherbConstants.ADMIN);
		return userProfile;
	}
	
	private TransportMode tranportModeParser(TransportModeRequest req) {
		TransportMode tranportMode = new TransportMode();
		tranportMode.setCreatedDate(new Date());
		tranportMode.setFromCountry(req.getFromCountry());
		tranportMode.setModeType(req.getModeType());
		tranportMode.setModifiedDate(new Date());
		tranportMode.setToCountry(req.getToCountry());
		return tranportMode;
	}
	
	//transportModeService
	private RoadTimings roadTimingsParser(RoadTimeRequest req) {
		RoadTimings road = new RoadTimings();
		road.setCreatedDate(new Date());
		road.setKilometer(req.getKilometer());
		road.setModifiedDate(new Date());
		road.setTimeInHrs(req.getTimeInHrs());
		road.setTimingsId(req.getTimingsId());
		return road;
	}
	private FlightSeaTimings modeTimingsParser(ModeTimingsRequest req) {
		FlightSeaTimings flightSeaTimings = new FlightSeaTimings();
		flightSeaTimings.setAirTime(req.getAirTime());
		flightSeaTimings.setCreatedDate(new Date());
		flightSeaTimings.setFromCountry(req.getFromCountry());
		flightSeaTimings.setMode(req.getMode());
		flightSeaTimings.setModifiedDate(new Date());
		flightSeaTimings.setSeaTime(req.getSeaTime());
		flightSeaTimings.setToCountry(req.getToCountry());
		
		return flightSeaTimings;
	}
	private PortEntity portDataParser(PortRequest req) {
		PortEntity port = new PortEntity();
		port.setCreatedDate(new Date());
		port.setFromCountry(req.getFromCountry());
		port.setFromPort(req.getFromPort());
		port.setModifiedDate(new Date());
		port.setToCountry(req.getToCountry());
		port.setToPort(req.getToPort());
		return port;
	}
	private Warehouse warehouseParser(WarehouseRequest warehouseReq) {
		Warehouse warehouse = new Warehouse();
		warehouse.setAddress(warehouseReq.getAddress());
		warehouse.setPincode(warehouseReq.getPincode());
		warehouse.setWarehouseName(warehouseReq.getWarehouseName());
		return warehouse;
	}
	
	//String strDate = "11-12-2020 15:23:10";
	private ProductQuantity prodQuantityParser(ProdQuantityRequest prodReq) {
		ProductQuantity prodQuantity = new ProductQuantity();
		prodQuantity.setBatch(prodReq.getBatch());
		prodQuantity.setBatchId(prodReq.getBatchId());
		prodQuantity.setModifiedDate(new Date());
		if(prodReq.getQid() == 0)
		prodQuantity.setCreatedDate(new Date());
		else
		prodQuantity.setQid(prodReq.getQid());
		
		prodQuantity.setExpiryDate(trueherbService.convertStringToDate(prodReq.getExpiryDate()));
		prodQuantity.setProductId(prodReq.getProductId());
		prodQuantity.setProductName(prodReq.getProductName());
		prodQuantity.setQuantity(prodReq.getQuantity());
		prodQuantity.setWarehouseId(prodReq.getWarehouseId());
		prodQuantity.setWarehouseName(prodReq.getWarehouseName());
		
		return prodQuantity;
	}
	
	private Batch batchDataParser(BatchRequest req) {
		Batch batch= new Batch();
		batch.setBatchName(req.getBatchName());
		batch.setModifiedDate(new Date());
		if(req.getBid() == 0) {
			batch.setCreatedDate(new Date());
		}else {
			batch.setBid(req.getBid());
		}
		return batch;
	}
	private LegalEntity legalEntityParser(LegalEntityRequest req) {
		LegalEntity legalEntity = new LegalEntity();
		legalEntity.setCoutry(req.getCoutry());
		legalEntity.setEntityName(req.getEntityName());
		legalEntity.setPincode(req.getPincode());
		legalEntity.setModifiedDate(new Date());
		if(req.getLegalEntityId() ==0)
			legalEntity.setCreatedDate( new Date());
		else
			legalEntity.setLegalEntityId(req.getLegalEntityId());
		
		
		
		return legalEntity;
	}
	
	
	private String getBase64DecryptionPwd(String str) {
		byte[] actualByte = Base64.getDecoder().decode(str);
		return new String(actualByte);

	}
	 
	
	private String getBase64EncryptionPwd(String str) {
		return Base64.getEncoder().encodeToString(str.getBytes());
	}
	
	static String getAlphaNumericString(int n, String name) {
		String autoGeneratePwd = (name.length() > 3) ? name.substring(0, 4) : name.substring(0, name.length() - 1);
		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return autoGeneratePwd + sb.toString();
	}


}
