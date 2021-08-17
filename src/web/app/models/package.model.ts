export interface Package {
	packageId: number;
	providerName: string;
	source?: string;
	destination?: string;
	status: string;
}

export interface PackageUpdate {
	packageId: number;
  updateDate: string;
  notes?: string;
}
