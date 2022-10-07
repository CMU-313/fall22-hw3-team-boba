'use strict';

/**
 * Document view content controller.
 */
angular.module('docs').controller('DocumentViewReview', function($scope, $rootScope, $stateParams, Restangular, $dialog, $state, Upload, $translate, $uibModal) {
    $scope.displayMode = _.isUndefined(localStorage.fileDisplayMode) ? 'grid' : localStorage.fileDisplayMode;
    $scope.openedFile = undefined;
    /**
     * File has been drag & dropped.
     */
    $scope.fileDropped = function(files) {
        if (!$scope.document.writable) {
            return;
        }

        if (files && files.length) {
            // Sort by filename
            files = _.sortBy(files, 'name');

            // Adding files to the UI
            var newfiles = [];
            _.each(files, function(file) {
                var newfile = {
                    progress: 0,
                    name: file.name,
                    create_date: new Date().getTime(),
                    mimetype: file.type,
                    status: $translate.instant('document.view.content.upload_pending')
                };
                $scope.files.push(newfile);
                newfiles.push(newfile);
            });

            // Uploading files sequentially
            var key = 0;
            var then = function() {
                if (files[key]) {
                    $scope.uploadFile(files[key], newfiles[key++]).then(then);
                }
            };
            then();
        }
    };

    /**
     * Upload a file.
     */
    $scope.uploadFile = function(file, newfile, previousFileId) {
        // Upload the file
        newfile.status = $translate.instant('document.view.content.upload_progress');
        return Upload.upload({
                method: 'PUT',
                url: '../api/file',
                file: file,
                fields: {
                    id: $stateParams.id,
                    previousFileId: previousFileId
                }
            })
            .progress(function(e) {
                newfile.progress = parseInt(100.0 * e.loaded / e.total);
            })
            .success(function(data) {
                // Update local model with real data
                newfile.id = data.id;
                newfile.size = data.size;

                // New file uploaded, increase used quota
                $rootScope.userInfo.storage_current += data.size;
            })
            .error(function(data) {
                newfile.status = $translate.instant('document.view.content.upload_error');
                if (data.type === 'QuotaReached') {
                    newfile.status += ' - ' + $translate.instant('document.view.content.upload_error_quota');
                }
            });
    };
});