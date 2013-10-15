(function(){ mollify.ui.texts.add('en', {

decimalSeparator: ".",
groupingSeparator: ",",
zeroDigit: "0",
plusSign: "+",
minusSign: "-",

days: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
daysShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
daysMin: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"],
months: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
monthsShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
today: "Today",
weekStart: 1,

fileSizeFormat: "#.#",
sizeInBytes: "{0} bytes",
sizeOneByte: "1 byte",
sizeOneKilobyte: "1 kB",
sizeInKilobytes: "{0} kB",
sizeInMegabytes: "{0} MB",
sizeInGigabytes: "{0} GB",
dataRateKbps: "kb/s",

shortDateTimeFormat: "M/d/yyyy h:mm:ss tt",
shortDateFormat: "M/d/yyyy",
timeFormat: "h:mm:ss a",

pleaseWait: "Please wait...",
yes: "Yes",
no: "No",
ok: "OK",
dialogCancelButton: "Cancel",
dialogCancel: "Cancel",
dialogClose: "Close",
dialogSave: "Save",
errorDialogTitle: "Mollify",
errorDialogUnknownError: "Request failed",
errorDialogMessage_204: "File already exists.",
errorDialogMessage_205: "Folder already exists.",

loginDialogTitle: "Login",
loginDialogUsername: "Username",
loginDialogPassword: "Password",
loginDialogRememberMe: "Remember me",
loginDialogResetPassword: "Forgot password?",
loginDialogRegister: "Register",
loginDialogLoginButton: "Log in",
loginDialogLoginFailedMessage: "Login failed, user name or password was incorrect.",
resetPasswordPopupMessage: "Enter your email address",
resetPasswordPopupButton: "Reset",
resetPasswordPopupResetSuccess: "Your new password has been reset and sent to your email address.",
resetPasswordPopupResetFailed: "Password reset failed",

mainviewMenuTitle: "Files",
mainViewSearch: "Search",
mainViewChangePasswordTitle: "Change password...",
mainViewAdministrationTitle: "Administration...",
mainViewLogoutTitle: "Logout",
mainViewRootsTitle: "Folders",
mainviewCreateFolderTitle : "Create new folder:",
mainviewCreateFolderName : "Folder name",
mainviewDescriptionHint: "Enter description here",
mainviewFolderNotFound: "Folder \"{0}\" was not found",

mainviewChangePasswordTitle: "Change password",
mainviewChangePasswordOld: "Old password",
mainviewChangePasswordNew: "New password",
mainviewChangePasswordVerify: "Verify password",
mainviewChangePasswordAction: "Change",
mainviewChangePasswordErrorValueMissing: "Missing",
mainviewChangePasswordErrorConfirm: "Passwords don't match",
mainviewChangePasswordSuccess: "Your password has been changed",
mainviewChangePasswordError: "Could not change password, authentication failed",

mainViewSearchResultsTitle : "Found {0} results",
mainViewSearchResultsDesc : "Searching \"{0}\"",
mainViewSearchResultTooltipMatches : "Matches: ",

uploaderWidgetTitle: "Upload new file(s) by clicking or dragging files here:",
mainviewUploadProgressOneMessage: "Uploading 1 file",
mainviewUploadProgressManyMessage: "Uploading {0} files",
mainviewFileUploadFailed: "File upload failed",
mainviewFileUploadComplete: "File upload complete",
mainviewRetrieveFileTitle: "Retrieve file from URL:",
mainviewRetrieveUrlTitle: "File URL",
mainviewRetrieveFileResourceNotFound: "Could not retrieve file, resource not found: {0}",

fileListColumnTitleName: "Name",
fileListColumnTitlePath: "Path",
fileListColumnTitleSize: "Size",
fileListColumnTitleLastModified: "Last modified",

fileActionDownloadTitle: "Download",
fileActionRenameTitle: "Rename...",
fileActionDeleteTitle: "Delete",

secondaryActions: "Actions",

itemcontextDescriptionHint: "Enter description here",

mainViewFileViewSelectNone: "Select none",
mainViewFileViewSelectAll: "Select all",

actionDownloadItem: "Download",
actionCopyItem: "Copy...",
actionCopyMultiple: "Copy...",
actionMoveItem: "Move...",
actionMoveMultiple: "Move...",
actionCopyItemHere: "Copy here...",
actionDeleteItem: "Delete",
actionDeleteMultiple: "Delete ",
actionRenameItem: "Rename...",

actionDeniedDelete: "Item \"{0}\" cannot be deleted because:",
actionDeniedDeleteMany: "One or more items cannot be deleted because:",
actionAcceptDelete: "Are you sure you want to delete \"{0}\":",
actionAcceptDeleteMany: "Are you sure you want to delete {0} items:",

fileItemDetailsGroupFile: "File",
fileItemContextDataName: "Name",
fileItemContextDataPath: "Path",
fileItemContextDataSize: "File size",
fileItemContextDataExtension: "Extension",
fileItemContextDataLastModified: "Last modified",
fileItemDetailsGroupExif: "Image information",
fileItemContextDataImageSize: "Image size",
fileItemContextDataImageSizePixels: "{0} pixels",

copyHereDialogTitle: "Copy Here",
copyHereDialogMessage: "Enter the name for the copy:",
copyFileDialogAction: "Copy",

copyFileDialogTitle: "Copy",
copyFileMessage: "Select folder where \"{0}\" is copied into:",

copyMultipleFileDialogTitle: "Copy",
copyMultipleFileMessage: "Select folder where {0} files are copied into:",

renameDialogTitleFile: "Rename File",
renameDialogTitleFolder: "Rename Folder",
renameDialogNewName: "Enter new name:",
renameDialogRenameButton: "Rename",

moveFileDialogTitle: "Move File",
moveDirectoryDialogTitle: "Move Folder",
moveDirectoryMessage: "Select folder where \"{0}\" is moved into:",
moveFileMessage: "Select folder where \"{0}\" is moved into:",
moveFileDialogAction: "Move",
moveDirectoryDialogAction: "Move",

deleteFileConfirmationDialogTitle: "Delete File",
deleteFolderConfirmationDialogTitle: "Delete Folder",
confirmFileDeleteMessage: "Are you sure you want to delete \"{0}\"?",
confirmFolderDeleteMessage: "Are you sure you want to delete \"{0}\" and all its files and subfolders?",

dropboxEmpty: "Clear",

pluginItemDetailsContextTitle: "Details",

pluginFileViewerEditorPreview: "Preview",
pluginFileViewerEditorView: "View",
pluginFileViewerEditorEdit: "Edit",
fileViewerEditorViewInNewWindowTitle: "Open in New Window",
fileViewerEditorViewEditDialogTitle: "Edit",
fileViewerEditorViewEditTitle: "Edit",

pluginCommentContextTitle: "Comments",
commentsDialogTitle: "Comments",
commentsDialogNoComments: "No comments",
commentsDialogAddButton: "Add",
commentsDetailsCount: "Comments",
commentsDialogRemoveComment: "Remove",

pluginPermissionsAction: "Permissions...",
pluginPermissionsContextTitle: "Permissions",
pluginPermissionsContextEditAction: "Edit...",
pluginPermissionsEditDialogTitle: "Permissions",
pluginPermissionsValueRW: "Read&Write&Delete",
pluginPermissionsValueWD: "Read&Write, No delete",
pluginPermissionsValueRO: "Read Only",
pluginPermissionsValueNO: "No Rights",
pluginPermissionsEditColUser: "User",
pluginPermissionsEditColPermission: "Permission",
pluginPermissionsEditRemove: "Remove",
pluginPermissionsEditNewTitle: "Add or Update Permission",
pluginPermissionsEditNoUser: "Select user",
pluginPermissionsEditNoPermission: "Select permission",

pluginDropboxAddTo: "Add to Dropbox",

pluginArchiverCompress: "Compress...",
pluginArchiverExtract: "Extract",
pluginArchiverDownloadCompressed: "Download compressed",
pluginArchiverCompressDialogTitle: "Compress",
pluginArchiverCompressDialogMessage: "Enter the name for the compressed package:",
pluginArchiverCompressDialogAction: "Compress",
pluginArchiverCompressSelectFolderDialogMessage: "Select the folder where the package is created into:",

itemContextShareTitle: 'Share',
itemContextShareMenuTitle: 'Share…',
pluginShareFilelistColOtherShared: 'This item has been shared by other users',
pluginShareActionValidationDeleteShared: '\"{0}\" has been shared',
pluginShareActionValidationDeleteSharedOthers: '\"{0}\" has been shared by other users',
shareDialogTitle: 'Shares',
shareDialogNoShares: 'No shares',
shareDialogShareFileTitle: "Share file download",
shareDialogShareFolderTitle: 'Share folder upload',
shareDialogUnnamedShareTitle: 'Unnamed',
shareDialogItemLinkTitle: 'Show/hide link',
shareDialogItemInactiveLinkTitle: 'Inactive',
shareDialogShareLinkInstructions: 'Copy&paste the link below to access this share:',
shareDialogShareLinkCopyToClipboard: 'Copy link to clipboard',
shareDialogShareAddButton: 'Create new…',
shareDialogShareEditButton: 'Edit',
shareDialogShareAddTitle: 'Create New Share',
shareDialogShareEditTitle: 'Edit Share',
shareDialogShareRemoveButton: 'Remove',
shareDialogShareCreateNewTitle: 'Create New Share',
shareDialogShareNameTitle: 'Name (optional):',
shareDialogShareLinkTitle: 'Link',
shareDialogShareActiveTitle: 'Active:',
shareDialogShareValidityTitle: 'Validity',
shareDialogShareExpirationTitle: 'Expires at',
shareDialogSave: 'Save',
shareDialogCancel: 'Cancel',
pluginShareConfigViewNavTitle: "Shares",
pluginShareConfigViewCountTitle: "Count",

pluginItemCollectionsNavTitle: "Stored",
pluginItemCollectionStore: "Store...",
pluginItemCollectionStoreDialogTitle: "Store Item Collection",
pluginItemCollectionStoreDialogMessage: "Enter name for the collection:",
pluginItemCollectionStoreDialogAction: "Store",
pluginItemCollectionShareTitle: "Share Stored Collection",
pluginItemCollectionsEditDialogTitle: "Edit Collection: {0}",
pluginItemCollectionsEditDialogRemove: "Remove",
pluginItemCollectionsNavRemove: "Remove",
pluginItemCollectionsNavShare: "Share...",
pluginItemCollectionsNavEdit: "Edit...",

configviewMenuTitle: "Configuration",
configViewUserNavTitle: "User",

configUserAccountNavTitle: "Account",
configUserAccountUsernameTitle: "User",
configUserAccountPasswordTitle: "Password",
configUserAccountPasswordAction: "Change",

configViewAdminNavTitle: "Admin",
configAdminUsersNavTitle: "Users",
configAdminGroupsNavTitle: "Groups",
configAdminUsersPermissionModeAdmin: "Administrator",
configAdminTableIdTitle: "Id",

configAdminActionRemoveTitle: "Remove",
configAdminActionEditTitle: "Edit",

configAdminUsersNameTitle: "Username",
configAdminUsersPermissionTitle: "Default permission",
configAdminUsersEmailTitle: "Email",
configAdminUsersUserDialogEditTitle: "Edit User",
configAdminUsersUserDialogAddTitle: "Add New User",
configAdminUserDialogUsernameTitle: "Username",
configAdminUserDialogEmailTitle: "Email",
configAdminUserDialogPasswordTitle: "Password",
configAdminUserDialogGeneratePassword: "Generate",
configAdminUserDialogPermissionModeTitle: "Default permission",
configAdminUserDialogAuthenticationTitle: "Authentication",
configAdminUsersUserDialogAuthDefault: "Default ({0})",
configAdminUserDialogExpirationTitle: "Expiration",
configAdminUsersFoldersTitle: "Folders",
configAdminUsersGroupsTitle: "Groups",
configAdminUsersFolderNameTitle: "User Folder Name",
configAdminUsersFolderDefaultNameTitle: "Default name",
configAdminUsersGroupNameTitle: "Group name",
configAdminUserAddFolderTitle: "Add User Folder",
configAdminUserAddFolderMessage: "Select folder(s) to be added to the user:",
configAdminUserAddGroupTitle: "Add User Group",
configAdminUserAddGroupMessage: "Select group(s) to be added to the user:",
configAdminUsersRemoveUsersConfirmationTitle: "Remove Users",
configAdminUsersRemoveUsersConfirmationMessage: "Are you sure you want to remove {0} users?",
configAdminUsersRemoveUserConfirmationTitle: "Remove User",
configAdminUsersRemoveUserConfirmationMessage: "Are you sure you want to remove user \"{0}\"?",
configAdminUsersChangePasswordDialogTitle: "Change Password for \"{0}\"",
configAdminUserSearchOptionsTitle: "Filter",
configAdminUserSearchOptionsNameTitle: "Name",
configAdminUserSearchOptionsEmailTitle: "Email",
configAdminUsersActionChangePasswordTitle: "Change Password",

configAdminFoldersNavTitle: "Folders",
configAdminFoldersNameTitle: "Folder name",
configAdminFoldersPathTitle: "Path",
configAdminFoldersFolderDialogEditTitle: "Edit Folder",
configAdminFoldersFolderDialogAddTitle: "Add New Folder",
configAdminFolderAddUserTitle: "Add Folder User",
configAdminFolderAddUserMessage: "Select user(s) or group(s) to be added:",
configAdminFolderUserTypeTitle: "Type",
configAdminFolderDialogNameTitle: "Folder name",
configAdminFolderDialogPathTitle: "Server filesystem path",
configAdminUsersFolderDefaultName: "{0} (default)",
configAdminFolderUsersTitle: "Folder Users and Groups",
configAdminFoldersFolderDialogAddFolderDoesNotExist: "Folder does not exist, create folder?",
configAdminFoldersRemoveFoldersConfirmationTitle: "Remove Folders",
configAdminFoldersRemoveFoldersConfirmationMessage: "Are you sure you want to remove {0} folders?",
configAdminFoldersRemoveFolderConfirmationTitle: "Remove Folder",
configAdminFoldersRemoveFolderConfirmationMessage: "Are you sure you want to remove folder \"{0}\"?",

configAdminGroupsDialogAddTitle: "Add Group",
configAdminGroupsDialogEditTitle: "Edit Group",
configAdminGroupDialogNameTitle: "Name",
configAdminGroupsDescriptionTitle: "Description",
configAdminGroupsUsersTitle: "Group Users",
configAdminGroupsFoldersTitle: "Group Folders",
configAdminGroupAddFolderTitle: "Add Group Folder",
configAdminGroupAddFolderMessage: "Select folder(s) to be added:",
configAdminGroupAddUserTitle: "Add Group User",
configAdminGroupAddUserMessage: "Select user(s) to be added:",
configAdminGroupsRemoveGroupsConfirmationTitle: "Remove Groups",
configAdminGroupsRemoveGroupsConfirmationMessage: "Are you sure you want to remove {0} groups?",
configAdminGroupsRemoveGroupConfirmationTitle: "Remove Group",
configAdminGroupsRemoveGroupConfirmationMessage: "Are you sure you want to remove group \"{0}\"?",

registrationNewTitle: "Register",
registrationNewName: "Name",
registrationNewPw: "Password",
registrationNewPwConfirm: "Confirm password",
registrationNewEmail: "Email",
registrationNewSubmit: "Register",
registrationFailed: "Registration failed, please try again later",
registrationNewSuccessTitle: "Registration successful",
registrationNewSuccessMessage: "Complete the registration by following the instructions sent to your email address.",
registrationInvalidConfirm: "Invalid registration confirmation",
registrationConfirmTitle: "Confirm Registration",
registrationConfirmMessage: "Complete your registration by entering the confirmation key sent to you via email.",
registrationConfirmEmail: "Email",
registrationConfirmKey: "Confirmation key",
registrationConfirmSubmit: "Confirm",
registrationConfirmFailed: "Registration could not be confirmed",
registrationConfirmSuccessTitle: "Registration complete",
registrationConfirmSuccessMessage: "Your registration is now complete, and you can log into Mollify.",
registrationContinueButton: "Log into Mollify"
})})();
