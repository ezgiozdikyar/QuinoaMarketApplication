package com.graduationproject.quinoamarketapp.business.concretes;

import com.graduationproject.quinoamarketapp.business.abstracts.FarmerService;
import com.graduationproject.quinoamarketapp.business.abstracts.ImageService;
import com.graduationproject.quinoamarketapp.entity.Farmer;
import com.graduationproject.quinoamarketapp.model.FarmerRequestDTO;
import com.graduationproject.quinoamarketapp.model.FarmerResponseDTO;
import com.graduationproject.quinoamarketapp.repository.FarmerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class FarmerManager implements FarmerService {
    @Autowired
    private FarmerRepository farmerRepository;
    @Autowired
    private ImageService imageService;
    private ModelMapper modelMapper;
    @Override
    public FarmerResponseDTO getById(Long id) throws Exception {
        Farmer farmer = farmerRepository.findById(id).orElse(null);
        if(farmer == null) {
            throw new Exception("Farmer not found with id "+ id);
        }
        return modelMapper.map(farmer,FarmerResponseDTO.class);
    }

    @Override
    public List<FarmerResponseDTO> getAll() {
        List<Farmer> farmers = farmerRepository.findAll();
        List<FarmerResponseDTO> farmerResponseList = modelMapper.map(farmers, new TypeToken<List<FarmerResponseDTO>>() {}.getType());
        return farmerResponseList;
    }

    @Override
    public FarmerResponseDTO add(FarmerRequestDTO farmerRequest) {
        Farmer farmer = modelMapper.map(farmerRequest,Farmer.class);
        farmerRepository.save(farmer);
        FarmerResponseDTO farmerResponse = modelMapper.map(farmer,FarmerResponseDTO.class);
        return farmerResponse;
    }

    @Override
    public FarmerResponseDTO update(FarmerRequestDTO farmerRequest) throws Exception {
        Farmer farmer = farmerRepository.findById(farmerRequest.getId()).orElse(null);
        if(farmer == null){
            throw new Exception("Farmer not found with id "+ farmerRequest.getId());
        }
        farmer = modelMapper.map(farmerRequest,Farmer.class);
        farmerRepository.save(farmer);
        FarmerResponseDTO farmerResponse = modelMapper.map(farmer,FarmerResponseDTO.class);
        return farmerResponse;
    }

    public FarmerResponseDTO updateProfilePhoto(Long id, MultipartFile profilePhoto) throws Exception{
        Farmer farmer = farmerRepository.findById(id).orElse(null);
        if(farmer == null){
            throw new Exception("Farmer not found with id "+ id);
        }
        farmer.setProfilePhoto(imageService.add(profilePhoto));
        farmerRepository.save(farmer);
        FarmerResponseDTO farmerResponse = modelMapper.map(farmer,FarmerResponseDTO.class);
        return farmerResponse;
    }

    @Override
    public void delete(Long id) throws Exception {
        Farmer farmer = farmerRepository.findById(id).orElse(null);
        if(farmer == null){
            throw new Exception("Farmer not found with id "+ id);
        }
        farmerRepository.deleteById(id);
    }
}
