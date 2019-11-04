/*
 * This script orchestrates UTP 2.0 pipelines.
 */

import groovy.json.JsonOutput
import hudson.AbortException
import hudson.console.ModelHyperlinkNote
import hudson.plugins.git.GitException
import java.util.logging.Level

// Other Jenkins parameters:
GIT_CREDENTIALS = 'c4153dd1-4ef2-44d7-8104-5ef64b2dea47' // The jenkins credentials id to use.
DEMO_GIT_URL = 'https://github.com/Poomagan/Demo.git' // The repo to checkout from.
DEMO_2_GIT_URL = 'https://github.com/Poomagan/Demo_2.git'

node('master') {

// Stage 1
	stage('Git_Checkout Stage') {
	if (DEBUG) {input 'Start Git Checkout?'}
		try {
				dir('Demo'){
				result = checkout(
					poll: false,
					scm: [
						$class: 'GitSCM',
						branches: [[name: 'master']],
						doGenerateSubmoduleConfigurations: false,
						extensions: [[$class: 'WipeWorkspace']],
						userRemoteConfigs: [[
							credentialsId: GIT_CREDENTIALS, 
							url: DEMO_GIT_URL
						]]
					]
				)
				}
			} catch(MultipleFailureException ex) {
				throw ex
			} catch(Exception ex) {
				throw ex
			}
		try {
				dir('Demo_2'){
				result = checkout(
					poll: false,
					scm: [
						$class: 'GitSCM',
						branches: [[name: 'master']], 
						doGenerateSubmoduleConfigurations: false,
						extensions: [[$class: 'WipeWorkspace']],
						userRemoteConfigs: [[
							credentialsId: GIT_CREDENTIALS, 
							url: DEMO_2_GIT_URL
						]]
					]
				)
				}
			} catch(MultipleFailureException ex) {
				throw ex
			} catch(Exception ex) {
				throw ex
			}
	}

}
