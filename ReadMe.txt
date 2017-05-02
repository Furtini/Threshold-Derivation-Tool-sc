TDTool (Threshold Derivation Tool): ReadMe.txt
==============================================

This document provides a documentation to support the extension of TDTool to provide new threshold derivation methods. Section 1 overviews the source code organization of TDTool. Section 2 provides tips to extend TDTool with new methods.


1. Source Code Organization of TDTool
-------------------------------------
The source code project of TDtool is organized in 2 packages. Each package, with subpackages, are described as follows.
- "tdt": the main package of TDTool
	- "methods": a subpackage that implements the processing of each threshold derivation method
		- There is one package for each method
	- "userinterface": a subpackage that implements user interface for the TDTool screens (main tool screens and each implemented method)
		- There is one package for each method
		- Here we implement the classes for the main tool screen
		- There is a subpackage, called "resource", that contains pictures used to compose the tool's interface
- "org.scitools.metrics": the main package of the Oliveira's supporting tool (called RTTool), integrated to TDTool
	Notes: (i) We did not change siginificantly the original source code of RTTool. (ii) We only removed commented legacy code, fixed some warnings, and other changes that does not impact the method implementation (input, processing, and output)


2. Step-by-Step to Extend TDTool with New Methods
-------------------------------------------------
There are two ways to extend TDTool for implementation of new threshold derivation methods. Each way is described as follows.

I. Extend the tool with a not previously implemented method: if a new method is not implemented in another source code project, we suggest the following steps.
	Step 1. Create a new package in "userinterface" and, then, implement the user interface (windows) for the new method
	Step 2. Create a new package in "methods" and, then, implement the new method's processing
	Step 3. Adapt the main screen of TDTool in "tdt.userinterface.MainWindow" to include the new method
		- Create a new button "Execute" and link it with the main screen of the new method

II. Extend the tool with an existing method implementation: if the new method has an existing implementation, we suggest the following steps to extend TDtool with the new method.
	Step 1. Copy the main package of the existing method implementation to the "src" folder
	Step 2. Adapt the main screen of TDTool in "tdt.userinterface.MainWindow" to include the new method
		- Create a new button "Execute" and link it with the main screen of the new method
	Step 3. Import all required libraries for solving dependencies with respect to the new method